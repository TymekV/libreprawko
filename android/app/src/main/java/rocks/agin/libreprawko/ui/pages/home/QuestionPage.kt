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
                    text = "Przewożone dziecko ma co najmniej 135 cm wzrostu, jest przypięte tylko pasami bezpieczeństwa, gdyż masa i wzrost uniemożliwia przewóz w foteliku lub innym urządzeniu przytrzymującym  dziecko",
                    selected = selected == 0,
                    onOptionSelected = { selected = 0 },
                    answerLetter = "A",
                )
                Answer(
                    text = "Nie, ponieważ przepisy dotyczące przewozu dzieci zezwalają na przewóz dzieci o wzroście co najmniej 135 cm bez fotelika lub innego urządzenia przytrzymującego dziecko tylko na tylnym siedzeniu pojazdu.",
                    selected = selected == 1,
                    onOptionSelected = { selected = 1 },
                    answerLetter = "B",
                )
                Answer(
                    text = "Kierowania nim przez osobę nieposiadającą uprawnienia do kierowania pojazdami i gdy nie ma możliwości zabezpieczenia pojazdu przez przekazanie go osobie znajdującej się w nim i posiadającej uprawnienia do kierowania tym pojazdem.",
                    selected = selected == 2,
                    onOptionSelected = { selected = 2 },
                    answerLetter = "C",
                )
            }

            RoundedCardContainer {
                Answer(
                    text = "20 km/h",
                    selected = selected == 0,
                    onOptionSelected = { selected = 0 },
                    answerLetter = "A",
                )
                Answer(
                    text = "30 km/h",
                    selected = selected == 1,
                    onOptionSelected = { selected = 1 },
                    answerLetter = "B",
                )
                Answer(
                    text = "40 km/h",
                    selected = selected == 2,
                    onOptionSelected = { selected = 2 },
                    answerLetter = "C",
                )
            }
        }
    }
}
