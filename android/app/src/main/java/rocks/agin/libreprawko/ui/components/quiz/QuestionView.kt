package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.db.models.Question
import rocks.agin.libreprawko.db.models.QuestionTranslation
import rocks.agin.libreprawko.db.models.QuestionType
import rocks.agin.libreprawko.ui.components.core.containers.RoundedCardContainer
import rocks.agin.libreprawko.ui.theme.LibrePrawkoTheme

@Composable
fun QuestionView(
    question: Question,
    translation: QuestionTranslation,
    selected: Int,
    onOptionSelected: (selected: Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 18.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(top = 6.dp),
        ) {
            Text(
                "Nr pytania: ${question.questionNumber} • ${question.points} punkty",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Text(
                translation.question,
                style =
                    MaterialTheme.typography.titleLarge.merge(
                        fontWeight = FontWeight.Medium,
                    ),
            )
        }

        QuizImage(modifier = Modifier.weight(1f))

        RoundedCardContainer {
            if (question.type == QuestionType.Specialist) {
                Answer(
                    text = translation.answerA,
                    selected = selected == 0,
                    onOptionSelected = { onOptionSelected(0) },
                    answerLetter = "A",
                )
                Answer(
                    text = translation.answerB,
                    selected = selected == 1,
                    onOptionSelected = { onOptionSelected(1) },
                    answerLetter = "B",
                )
                Answer(
                    text = translation.answerC,
                    selected = selected == 2,
                    onOptionSelected = { onOptionSelected(2) },
                    answerLetter = "C",
                )
            } else {
                Answer(
                    text = "Tak",
                    selected = selected == 0,
                    onOptionSelected = { onOptionSelected(0) },
                )
                Answer(
                    text = "Nie",
                    selected = selected == 1,
                    onOptionSelected = { onOptionSelected(1) },
                )
            }
        }
    }
}

@Preview
@Composable
fun BasicQuestionPreview() {
    val question =
        Question(
            id = 0,
            questionNumber = 6333,
            type = QuestionType.Basic,
            correctAnswer = "Tak",
            media = null,
            mediaType = null,
            points = 3
        )

    val translation = QuestionTranslation(
        id = 0,
        questionId = 0,
        question = "Z jaką maksymalną dopuszczalną prędkością możesz jechać na drodze za tym znakiem?",
        answerA = null,
        answerB = null,
        answerC = null
    )

    LibrePrawkoTheme(darkTheme = true) {
        Surface {
            QuestionView(question, translation, 0) { _ -> }
        }
    }
}

@Preview
@Composable
fun SpecialistQuestionPreview() {
    val question =
        Question(
            id = 0,
            questionNumber = 6333,
            type = QuestionType.Specialist,
            correctAnswer = "A",
            media = null,
            mediaType = null,
            points = 3
        )

    val translation = QuestionTranslation(
        id = 0,
        questionId = 0,
        question = "Z jaką maksymalną dopuszczalną prędkością możesz jechać na drodze za tym znakiem?",
        answerA = "20 km/h",
        answerB = "30 km/h",
        answerC = "40 km/h"
    )

    LibrePrawkoTheme(darkTheme = true) {
        Surface {
            QuestionView(question, translation, 0) { _ -> }
        }
    }
}
