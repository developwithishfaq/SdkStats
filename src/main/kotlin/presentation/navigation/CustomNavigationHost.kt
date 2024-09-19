package presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import core.composable.KeysCard
import core.composable.VerticalSpace
import navigation.NavigationHost
import navigation.composable
import presentation.LocalNavController
import presentation.home.HomeScreen
import presentation.routes.Screens

@Composable
fun CustomNavigationHost(
) {
    val controller = LocalNavController.current
    Column {
        KeysCard()
        VerticalSpace(5)
        NavigationHost(controller) {
            composable(Screens.HomeScreen.name) {
                HomeScreen()
            }
        }.build()
    }
}