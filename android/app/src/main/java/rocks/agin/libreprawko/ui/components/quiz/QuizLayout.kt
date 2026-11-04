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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.quiz.QuestionResult
import rocks.agin.libreprawko.ui.components.core.containers.RoundedCardContainer

@Composable
fun QuizLayout() {
    var selected by remember { mutableIntStateOf(0) }

    var checkShown by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(bottom = 14.dp, top = 6.dp),
    ) {
        QuizHeader(modifier = Modifier.padding(end = 18.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 18.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 6.dp),
            ) {
                Text(
                    "Nr pytania: 6333 • 3 punkty",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Text(
                    "Z jaką maksymalną dopuszczalną prędkością możesz jechać na drodze za tym znakiem?",
                    style =
                        MaterialTheme.typography.titleLarge.merge(
                            fontWeight = FontWeight.Medium,
                        ),
                )
            }

            QuizImage(modifier = Modifier.weight(1f))

            RoundedCardContainer {
                Answer(
                    text = "20 km/h",
                    selected = selected == 0,
                    onOptionSelected = { selected = 0 },
                    answerLetter = "A",
                )
                Answer(
                    text = "30 km/h",
                    selected = selected == 1,
                    onOptionSelected = { selected = 1 },
                    answerLetter = "B",
                )
                Answer(
                    text = "40 km/h",
                    selected = selected == 2,
                    onOptionSelected = { selected = 2 },
                    answerLetter = "C",
                )
            }


            val density = LocalDensity.current
            val bottomInset = WindowInsets.navigationBars.getBottom(density)
            val margin = with(density) { 14.dp.roundToPx() }

            AnimatedContent(
                targetState = checkShown,
                contentAlignment = Alignment.BottomCenter,
                transitionSpec = {
                    val spec = if (targetState) {
                        slideInVertically(tween(350)) { it + margin + bottomInset } togetherWith
                            fadeOut(tween(200))
                    } else {
                        fadeIn(tween(250, delayMillis = 150)) togetherWith
                            slideOutVertically(tween(350)) { it + margin + bottomInset }
                    }
                    spec.apply { targetContentZIndex = if (targetState) 1f else -1f }
                        .using(SizeTransform(clip = false))
                },
            ) { isExpanded ->
                if (isExpanded) {
                    QuizCheck(
                        result = QuestionResult.Correct(0),
                        onNext = {
                            checkShown = !checkShown
                        },
                        modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
                    )
                } else {
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
