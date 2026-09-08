package rocks.agin.libreprawko.db

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import rocks.agin.libreprawko.db.models.MediaTypeConverter
import rocks.agin.libreprawko.db.models.Question
import rocks.agin.libreprawko.db.models.QuestionTranslation
import rocks.agin.libreprawko.db.models.QuestionTypeConverter

@Database(
    entities = [Question::class, QuestionTranslation::class],
    version = 1,
)
@ColumnTypeConverters(
    MediaTypeConverter::class,
    QuestionTypeConverter::class,
)
abstract class AppDatabase : RoomDatabase()
