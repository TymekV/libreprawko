package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

enum class Mode {
    Next,
    Check,
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Controls(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    mode: Mode,
) {
    val previousInteractionSource = remember { MutableInteractionSource() }
    val nextInteractionSource = remember { MutableInteractionSource() }
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(previousInteractionSource) {
        previousInteractionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Press) {
                haptic.performHapticFeedback(HapticFeedbackType.Confirm)
            }
        }
    }

    LaunchedEffect(nextInteractionSource) {
        nextInteractionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Press) {
                haptic.performHapticFeedback(HapticFeedbackType.Confirm)
            }
        }
    }

    ButtonGroup(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(60.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        overflowIndicator = { // won't trigger, but still required
            menuState ->
            ButtonGroupDefaults.OverflowIndicator(menuState = menuState)
        },
    ) {
        val previousModifier =
            Modifier.weight(1f).animateWidth(previousInteractionSource).fillMaxHeight()
        customItem(
            buttonGroupContent = {
                FilledTonalButton(
                    onClick = {},
                    modifier = previousModifier,
                    interactionSource = previousInteractionSource,
                    shape = MaterialTheme.shapes.large,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            menuContent = { DropdownMenuItem(text = { Text("Poprzednie pytanie") }, onClick = {}) },
        )

        val nextModifier =
            Modifier.weight(2f).animateWidth(nextInteractionSource).fillMaxHeight()
        customItem(
            buttonGroupContent = {
                Button(
                    onClick = {},
                    modifier = nextModifier,
                    interactionSource = nextInteractionSource,
                    shape = MaterialTheme.shapes.large,
                ) {
                    when (mode) {
                        Mode.Next -> {
                            Text("Następne")
                            Spacer(Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = null,
                            )
                        }

                        Mode.Check -> {
                            Icon(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = null,
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Sprawdź")
                            Spacer(Modifier.width(8.dp))
                        }
                    }
                }
            },
            menuContent = { DropdownMenuItem(text = { Text("Poprzednie pytanie") }, onClick = {}) },
        )
    }
}
