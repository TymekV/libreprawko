package rocks.agin.libreprawko.db.models

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "question_translations")
data class QuestionTranslation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val questionId: Int,
    val question: String,
    val answerA: String?,
    val answerB: String?,
    val answerC: String?,
)
