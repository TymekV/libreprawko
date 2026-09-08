package rocks.agin.libreprawko.db.models

import androidx.room3.Embedded
import androidx.room3.Relation
import kotlinx.serialization.Serializable

data class QuestionWithTranslations(
    @Embedded val question: Question,
    @Relation(
        parentColumns = ["id"],
        entityColumns = ["questionId"],
    )
    val translations: List<QuestionTranslation>,
)
