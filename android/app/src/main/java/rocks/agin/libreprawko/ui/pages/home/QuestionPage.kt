package rocks.agin.libreprawko.ui.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QuestionPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Button(
            modifier = Modifier.padding(innerPadding),
            onClick = {},
        ) {
            Text(
                text = "question",
            )
        }
    }
}
