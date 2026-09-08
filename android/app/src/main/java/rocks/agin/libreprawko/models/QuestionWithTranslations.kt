package rocks.agin.libreprawko.models

import androidx.room3.Embedded
import androidx.room3.Relation

data class QuestionWithTranslations(
    @Embedded val question: Question,
    @Relation(
        parentColumns = ["id"],
        entityColumns = ["questionId"],
    )
    val translations: List<QuestionTranslation>,
)
