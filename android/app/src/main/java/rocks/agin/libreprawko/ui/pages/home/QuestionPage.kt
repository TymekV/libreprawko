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
import rocks.agin.libreprawko.ui.components.core.containers.RoundedCardContainer
import rocks.agin.libreprawko.ui.components.quiz.Answer
import rocks.agin.libreprawko.ui.components.quiz.Controls
import rocks.agin.libreprawko.ui.components.quiz.Mode
import rocks.agin.libreprawko.ui.components.quiz.QuizLayout

@Composable
fun QuestionPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var selected by remember { mutableIntStateOf(0) }

        Column(
            modifier =
                Modifier
                    .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            QuizLayout()
        }
    }
}
