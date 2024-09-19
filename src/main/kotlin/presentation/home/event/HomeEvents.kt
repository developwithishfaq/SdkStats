package presentation.home.event

sealed class HomeEvents {
    data class SetAdType(val type: String) : HomeEvents()
}