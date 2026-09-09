package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuizHeader(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
            )
        }

        LinearProgressIndicator(
            progress = { 0.6f },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(6.dp),
            drawStopIndicator = {},
        )
    }
}
