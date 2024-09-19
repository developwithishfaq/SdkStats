package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import navigation.NavController
import navigation.rememberNavController
import presentation.navigation.CustomNavigationHost
import presentation.routes.Screens


val LocalNavController = compositionLocalOf<NavController> {
    error("")
}

@Composable
fun DashboardApp() {
    val navController by rememberNavController(Screens.HomeScreen.name)
    CompositionLocalProvider(LocalNavController provides navController) {
        CustomNavigationHost()
    }
}