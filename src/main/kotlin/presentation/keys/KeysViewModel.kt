package presentation.keys

import data.utils.MyPref
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import presentation.keys.events.KeysScreenEvent
import presentation.keys.state.KeysState


class KeysViewModel(
    private val prefs: MyPref
) {
    private val _state = MutableStateFlow(KeysState())
    val state = _state.asStateFlow()


    fun refresh() {
        _state.update {
            it.copy(
                key = prefs.dbSecretKey,
                url = prefs.databaseUrl
            )
        }
    }


    fun onEvent(event: KeysScreenEvent) {
        when (event) {
            is KeysScreenEvent.SetKey -> {
                _state.update {
                    it.copy(
                        key = event.key
                    )
                }
            }

            is KeysScreenEvent.SetUrl -> {
                _state.update {
                    it.copy(
                        url = event.url
                    )
                }
            }

            KeysScreenEvent.Update -> {
                prefs.setDatabaseValue(key = state.value.key, url = state.value.url)
            }

            KeysScreenEvent.Reset -> {
                prefs.setDatabaseValue(
                    key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRuYnNqcXNjcXZocGZncGR4em1hIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyNjU2NTA5OCwiZXhwIjoyMDQyMTQxMDk4fQ.2jw2TonHK_qbNaycn2kzSrLtOHYVrzvA3MRR9Co1o9o",
                    url = "https://dnbsjqscqvhpfgpdxzma.supabase.co"
                )
                refresh()
            }

            KeysScreenEvent.HideShow -> {
                _state.update {
                    it.copy(
                        showCard = it.showCard.not()
                    )
                }
            }
        }
    }

}