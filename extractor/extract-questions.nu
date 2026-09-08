#!/usr/bin/env nu

# https://www.gov.pl/web/infrastruktura/prawo-jazdy
const FILE_URL = "https://www.gov.pl/attachment/a5c6c329-28a5-4274-a1a8-e2813f0a51bd"
const LOCALES = [PL, EN, D, UA]

def localized-key [key: string, locale: string] {
    if $locale == "PL" {
        return $key
    }
    $"($key) [($locale)]"
}

def get-localized [key: string, locale: string] {
    $in | get (localized-key $key $locale)
}

let questions = http get $FILE_URL | from xlsx --prefer-integers --sheets [katalog] | get katalog | where Kategorie != null | update Kategorie { split row "," | str trim | where $it != "" }

let categories = $questions | get Kategorie | flatten | str join "," | split row "," | str trim | where $it != "" | uniq

let grouped = $questions | flatten Kategorie | group-by Kategorie

let base_dir = "questions"

$LOCALES | par-each { |locale|
    let base_dir = $base_dir | path join $locale
    mkdir $base_dir
    $grouped | items { |category, questions|
        let parsed_questions = $questions | each { |q|
            let type = match $q."Zakres struktury" {
                "PODSTAWOWY" => "basic"
                "SPECJALISTYCZNY" => "specialist"
            }
            let media = match $q.Media {
                "" => null
                _ => $q.Media
            }
            let media_type = if $media == null { null } else {
                match ($media | path parse | get extension) {
                    "jpg" => "image"
                    "wmv" => "video"
                    _ => null
                }
            }

            let base = {
                id: ($q."Numer pytania" | into int)
                type: $type,
                q: ($q | get-localized "Pytanie" $locale)
                correct: $q."Poprawna odp"
                media: $media
                media_type: $media_type
                points: ($q."Liczba punktów" | into int)
            }
            let specialist_answers = if $type == "specialist" {
                {
                    a: ($q | get-localized "Odpowiedź A" $locale)
                    b: ($q | get-localized "Odpowiedź B" $locale)
                    c: ($q | get-localized "Odpowiedź C" $locale)
                }
            } else {
                {}
            }
            { ...$base, ...$specialist_answers }
        }
        $parsed_questions | to json --raw | save -f ($base_dir | path join $"($category).json")
    }
}

let category_stats = $grouped | items { |category, questions|
    {
        name: $category,
        questions_count: ($questions | length)
        basic_count: ($questions | where "Zakres struktury" == "PODSTAWOWY" | length)
        specialist_count: ($questions | where "Zakres struktury" == "SPECJALISTYCZNY" | length)
    }
}

let manifest = {
    categories: $category_stats
}

$manifest | to json --raw | save -f ($base_dir | path join "manifest.json")

echo "Done"
