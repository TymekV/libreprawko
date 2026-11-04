package rocks.agin.libreprawko.quiz

sealed interface QuestionResult {
    val correct: Int

    data class Correct(override val correct: Int) : QuestionResult

    data class Incorrect(
        val selected: Int,
        override val correct: Int,
    ) : QuestionResult
}
