package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.db.models.Question
import rocks.agin.libreprawko.db.models.QuestionTranslation
import rocks.agin.libreprawko.db.models.QuestionType
import rocks.agin.libreprawko.quiz.QuestionResult
import rocks.agin.libreprawko.ui.components.core.containers.RoundedCardContainer

@Composable
fun QuizLayout() {
    var selected by remember { mutableIntStateOf(0) }

    var checkShown by remember { mutableStateOf(false) }

    var currentQuestion by remember { mutableIntStateOf(0) }

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

    Column(
        modifier = Modifier.padding(top = 6.dp),
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            QuizHeader(modifier = Modifier.padding(end = 18.dp))

            QuestionView(question, translation, selected) { new -> selected = new }
        }

        AnimatedContent(
            targetState = checkShown,
            contentAlignment = Alignment.BottomCenter,
            transitionSpec = {
                val spec = if (targetState) {
                    slideInVertically(tween(350)) { it } togetherWith
                        fadeOut(tween(200))
                } else {
                    fadeIn(tween(250, delayMillis = 150)) togetherWith
                        slideOutVertically(tween(350)) { it }
                }
                spec.apply { targetContentZIndex = if (targetState) 1f else -1f }
                    .using(SizeTransform(clip = false))
            },
        ) { isExpanded ->
            if (isExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    QuizCheck(
                        result = QuestionResult.Correct(0),
                        onNext = {
                            checkShown = !checkShown
                        },
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 18.dp)
                        .padding(top = 12.dp, bottom = 14.dp)
                ) {
                    Controls(
                        onPrevious = {},
                        onNext = {
                            checkShown = !checkShown
                        },
                        mode = Mode.Check
                    )
                }
            }
        }
    }
}
