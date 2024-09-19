package presentation.keys.events

sealed class KeysScreenEvent {
    data class SetKey(val key: String) : KeysScreenEvent()
    data class SetUrl(val url: String) : KeysScreenEvent()
    data object Update : KeysScreenEvent()
    data object Reset : KeysScreenEvent()
    data object HideShow : KeysScreenEvent()
}