package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
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
) {
    val haptic = LocalHapticFeedback.current

    val onClickAction = {
        haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
        onOptionSelected()
    }

    ListItem(
        colors =
            ListItemDefaults.colors(
                containerColor =
                    if (selected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                headlineColor =
                    if (selected) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                trailingIconColor =
                    if (selected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
            ),
        modifier =
            modifier
                .clip(MaterialTheme.shapes.small)
                .clickable(onClick = onClickAction),
        headlineContent = {
            EmphasizedText(
                text,
                emphasized = selected,
                style = MaterialTheme.typography.labelLarge,
            )
        },
        trailingContent = {
            Checkbox(selected, onCheckedChange = null)
        },
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun EmphasizedText(
    text: String,
    emphasized: Boolean,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
) {
    val weight by animateFloatAsState(
        targetValue = if (emphasized) 600f else 400f,
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
    )
}
