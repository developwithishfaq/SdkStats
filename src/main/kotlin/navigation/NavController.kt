package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

class NavController(
    private val startDestination: String,
    private var backStackScreen: MutableSet<String> = mutableSetOf()
) {
    var currentScreen: MutableState<String> = mutableStateOf(startDestination)
    private var arguments = HashMap<String, Any?>()

    fun getString(key: String): String? {
        val value = arguments.getOrDefault(key, null)
        return if (value is String) {
            value
        } else {
            null
        }
    }

    fun getInt(key: String): Int? {
        val value = arguments.getOrDefault(key, null)
        return if (value is Int) {
            value
        } else {
            null
        }
    }

    fun navigate(route: String, args: HashMap<String, Any?> = hashMapOf()) {
        if (route != currentScreen.value) {
            if (
                backStackScreen.contains(currentScreen.value)
                && currentScreen.value != startDestination
            ) {
                backStackScreen.remove(currentScreen.value)
            }
            if (route == startDestination) {
                backStackScreen = mutableSetOf()
            } else {
                backStackScreen.add(currentScreen.value)
            }
            currentScreen.value = route
            arguments = args
        }
    }

    fun popBackStack() {
        if (backStackScreen.isNotEmpty()) {
            currentScreen.value = backStackScreen.last()
            backStackScreen.remove(currentScreen.value)
            arguments = hashMapOf()
        }
    }
}

@Composable
fun rememberNavController(
    startDestination: String,
    backStackScreen: MutableSet<String> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreen))
}