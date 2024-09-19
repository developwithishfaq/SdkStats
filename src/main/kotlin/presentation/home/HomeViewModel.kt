package presentation.home

import domain.repository.RequestsRepository
import domain.usecase.GetMainStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import presentation.home.event.HomeEvents
import presentation.home.state.HomeScreenState

class HomeViewModel(
    private val repository: RequestsRepository,
    private val getMainStates: GetMainStates
) {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    val stats =
        getMainStates.mainStates.stateIn(
            CoroutineScope(Dispatchers.IO), SharingStarted.WhileSubscribed(1000L),
            emptyList()
        )

    init {
        refresh()
    }

    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            getMainStates()/*
            val response = repository.getAllRequest()
            _state.update {
                it.copy(
                    request = response
                )
            }*/
        }
    }

    fun onEvent(events: HomeEvents) {
        when (events) {
            is HomeEvents.SetAdType -> {
                val type = if (state.value.selectedAdType == events.type) {
                    ""
                } else {
                    events.type
                }
                _state.update {
                    it.copy(
                        selectedAdType = type
                    )
                }
            }
        }
    }


}