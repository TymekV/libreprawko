package rocks.agin.libreprawko.ui.components.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// https://github.com/android/compose-samples/blob/06a7bf385c3cba50a991b3146b41bc5786b6598e/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/survey/question/MultipleChoiceQuestion.kt#L69
@Composable
fun Answer(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onOptionSelected: () -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color =
            if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            },
        border =
            BorderStroke(
                width = 1.dp,
                color =
                    if (selected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    },
            ),
        modifier =
            modifier
                .clip(MaterialTheme.shapes.small)
                .clickable(onClick = onOptionSelected),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text, Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.padding(8.dp)) {
                Checkbox(selected, onCheckedChange = null)
            }
        }
    }
}
