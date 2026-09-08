package rocks.agin.libreprawko.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rocks.agin.libreprawko.ui.components.quiz.Answer
import rocks.agin.libreprawko.ui.components.quiz.core.containers.RoundedCardContainer

@Composable
fun QuestionPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var selected by remember { mutableIntStateOf(0) }

        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RoundedCardContainer {
                Answer(
                    text = "20 km/h.",
                    selected = selected == 0,
                    onOptionSelected = { selected = 0 },
                )
                Answer(
                    text = "30 km/h.",
                    selected = selected == 1,
                    onOptionSelected = { selected = 1 },
                )
                Answer(
                    text = "40 km/h.",
                    selected = selected == 2,
                    onOptionSelected = { selected = 2 },
                )
            }
        }
    }
}
