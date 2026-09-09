package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ControlsButton() {
//    Button { }
}

@Composable
fun Controls() {
    ButtonGroup(
        overflowIndicator = { // won't trigger, but still required
            menuState ->
            ButtonGroupDefaults.OverflowIndicator(menuState = menuState)
        },
    ) {
        customItem(
            buttonGroupContent = {
                ControlsButton()
            },
            menuContent = { DropdownMenuItem(text = { Text("Poprzednie pytanie") }, onClick = {}) },
        )
    }
}
