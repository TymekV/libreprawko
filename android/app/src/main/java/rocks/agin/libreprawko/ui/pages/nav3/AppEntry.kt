package rocks.agin.libreprawko.ui.pages.nav3

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import rocks.agin.libreprawko.ui.pages.home.HomePage
import rocks.agin.libreprawko.ui.pages.home.QuestionPage

@Composable
fun AppEntry(backStack: NavBackStack<NavKey>) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Route.Home -> {
                    NavEntry(key) {
                        HomePage(navigateToQuestion = { id -> backStack.add(Route.Question(id)) })
                    }
                }

                is Route.Question -> {
                    NavEntry(key) {
                        QuestionPage()
                    }
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        },
    )
}
