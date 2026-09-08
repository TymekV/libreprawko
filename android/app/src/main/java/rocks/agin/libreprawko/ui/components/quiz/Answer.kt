package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.R
import kotlin.math.roundToInt

// https://github.com/android/compose-samples/blob/06a7bf385c3cba50a991b3146b41bc5786b6598e/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/survey/question/MultipleChoiceQuestion.kt#L69
// https://github.com/sameerasw/essentials/blob/9d5ba3be85b9f06077fbcc842ea70a025a213041/app/src/main/java/com/sameerasw/essentials/ui/core/cards/IconToggleItem.kt
@Composable
fun Answer(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onOptionSelected: () -> Unit,
    answerLetter: String? = null,
) {
    val haptic = LocalHapticFeedback.current

    val onClickAction = {
        haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
        onOptionSelected()
    }

    Row(
        modifier =
            modifier
                .clip(MaterialTheme.shapes.small)
                .background(
                    if (selected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                ).clickable(onClick = onClickAction)
                .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (answerLetter != null) {
                Text(
                    "$answerLetter.",
                    style = MaterialTheme.typography.labelLarge.merge(fontWeight = FontWeight.Bold),
                    color =
                        if (selected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                )
            }
            EmphasizedText(
                text,
                emphasized = selected,
                style = MaterialTheme.typography.labelLarge,
                color =
                    if (selected) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
            )
        }
        Checkbox(selected, onCheckedChange = null)
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun EmphasizedText(
    text: String,
    emphasized: Boolean,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    style: TextStyle = LocalTextStyle.current,
) {
    val weight by animateFloatAsState(
        targetValue = if (emphasized) 500f else 400f,
        label = "weight",
    )

    val fontFamily =
        remember(weight.roundToInt()) {
            FontFamily(
                Font(
                    R.font.google_sans_flex,
                    variationSettings =
                        FontVariation.Settings(
                            FontVariation.weight(weight.roundToInt()),
                        ),
                ),
            )
        }

    Text(
        text = text,
        modifier = modifier,
        style = style.merge(fontFamily = fontFamily),
        color = color,
    )
}
