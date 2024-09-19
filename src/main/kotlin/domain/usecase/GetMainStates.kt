package domain.usecase

import data.network.NetworkResponse
import domain.RequestStats
import domain.repository.RequestsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetMainStates(
    private val repository: RequestsRepository
) {

    private val _mainStates = MutableStateFlow<List<RequestStats>>(emptyList())
    val mainStates = _mainStates.asStateFlow()

    suspend operator fun invoke() {
        when (val response = repository.getAllRequest()) {
            is NetworkResponse.Success -> {
                val list = response.data ?: emptyList()
                val mainList = list.filter { it.adKey != null } // Filter out any null adKey values
                    .groupBy {
//                        it.adKey!!
                        Pair(it.adKey!!, it.adType!!)
                    } // Group by non-null adKey
                    .map { (adKey, requests) ->
                        val requestCount = requests.count { it.historyType == "request" }
                        val loadedCount = requests.count { it.historyType == "loaded" }
                        val failedCount = requests.count { it.historyType == "failed" }
                        val impressionCount = requests.count { it.historyType == "impression" }

                        RequestStats(
                            adType = adKey.second,
                            adKey = adKey.first,
                            request = requestCount,
                            loaded = loadedCount,
                            failed = failedCount,
                            impression = impressionCount
                        )
                    }
                _mainStates.update {
                    mainList
                }
                delay(3000)
                invoke()
            }

            is NetworkResponse.Failure -> {

            }

            is NetworkResponse.Idle -> {

            }

            is NetworkResponse.Loading -> {

            }
        }
    }
}