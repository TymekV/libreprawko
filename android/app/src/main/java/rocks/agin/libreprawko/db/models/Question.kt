package rocks.agin.libreprawko.db.models

import androidx.room3.ColumnInfo
import androidx.room3.ColumnTypeConverter
import androidx.room3.Entity
import androidx.room3.PrimaryKey

enum class QuestionType {
    Basic,
    Specialist,
}

class QuestionTypeConverter {
    @ColumnTypeConverter
    fun fromQuestionType(value: QuestionType): String = value.name.lowercase()

    @ColumnTypeConverter
    fun toQuestionType(value: String): QuestionType =
        QuestionType.entries.first { it.name.lowercase() == value }
}

enum class MediaType {
    Basic,
    Specialist,
}

class MediaTypeConverter {
    @ColumnTypeConverter
    fun fromMediaType(value: MediaType): String = value.name.lowercase()

    @ColumnTypeConverter
    fun toMediaType(value: String): MediaType =
        MediaType.entries.first { it.name.lowercase() == value }
}

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val id: Int,
    val type: QuestionType,
    @ColumnInfo(name = "correct_answer") val correctAnswer: String,
    val media: String?,
    @ColumnInfo(name = "media_type") val mediaType: MediaType?,
    val points: Int,
)
