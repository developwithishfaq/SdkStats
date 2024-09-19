package domain

fun List<RequestStats>.getShowRates(): String {
    val totalLoaded = this.sumOf { it.loaded }
    val totalImpression = this.sumOf { it.impression }

    return if (totalLoaded > 0) {
        ((totalImpression * 100) / totalLoaded).toString() + "%"
    } else {
        "---"
    }
}

fun List<RequestStats>.getMatchRates(): String {
    val totalRequests = this.sumOf { it.request }
    val totalLoaded = this.sumOf { it.loaded }

    return if (totalRequests > 0) {
        ((totalLoaded * 100) / totalRequests).toString() + "%"
    } else {
        "---"
    }
}

data class RequestStats(
    val adType: String,
    val adKey: String,
    val request: Int,
    val loaded: Int,
    val failed: Int,
    val impression: Int,
) {
    fun getShowRates(): String {
        return if (loaded > 0) {
            ((impression * 100) / loaded).toString() + "%"
        } else {
            "---"
        }
    }

    fun getMatchRates(): String {
        return if (request > 0) {
            ((loaded * 100) / request).toString() + "%"
        } else {
            "---"
        }
    }
}
