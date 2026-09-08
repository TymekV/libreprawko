package rocks.agin.libreprawko.models

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
    @PrimaryKey
    val id: Int,
    val type: QuestionType,
    val correctAnswer: String,
    val media: String?,
    val mediaType: MediaType?,
    val points: Int,
)
