package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingToolbarDefaults.animationSpec
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.quiz.QuestionResult
import rocks.agin.libreprawko.ui.theme.LibrePrawkoTheme

@Composable
fun QuizCheck(
    result: QuestionResult,
    modifier: Modifier = Modifier,
    explanation: String? = null,
    onNext: () -> Unit = {}
) {
    val backgroundColor = if (result is QuestionResult.Correct) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.errorContainer
    }

    val foregroundColor = if (result is QuestionResult.Correct) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onErrorContainer
    }

    Surface(
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        tonalElevation = 6.dp,
        shadowElevation = 4.dp,
        color = backgroundColor,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp, bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val boxColor = if (result is QuestionResult.Correct) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onError
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(boxColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (result is QuestionResult.Correct) Icons.Rounded.Check else Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = foregroundColor,
                    )
                }

                Text(
                    if (result is QuestionResult.Correct) "Poprawna odpowiedź" else "Niepoprawna odpowiedź",
                    style = MaterialTheme.typography.titleMedium.merge(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = foregroundColor,
                )
            }

            explanation?.let {
                Text(
                    explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = foregroundColor,
                )
            }

            val buttonColors = if (result is QuestionResult.Correct) {
                ButtonDefaults.buttonColors(containerColor = foregroundColor)
            } else {
                ButtonDefaults.buttonColors(
                    containerColor = foregroundColor,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            }

            Button(
                onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                colors = buttonColors,
                shape = RoundedCornerShape(20.dp),
            ) {
                Text(
                    "Dalej",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CorrectCheckPreview() {
    LibrePrawkoTheme(darkTheme = true) {
        QuizCheck(QuestionResult.Correct(0))
    }
}

@Preview
@Composable
fun IncorrectCheckPreview() {
    LibrePrawkoTheme(darkTheme = true) {
        QuizCheck(QuestionResult.Incorrect(1, 2))
    }
}

@Preview
@Composable
fun IncorrectCheckWithExplanationPreview() {
    LibrePrawkoTheme(darkTheme = true) {
        QuizCheck(
            QuestionResult.Incorrect(1, 2),
            explanation = "Zanim zbliżysz się do skrzyżowania, ustaw się na pasie zgodnym z kierunkiem planowanej jazdy. " +
                "Zrób to odpowiednio wcześniej, aby uniknąć gwałtownych manewrów."
        )
    }
}
