package data.network

sealed class NetworkResponse<T>(
    val data: T? = null,
    val error: String? = null,
    val originalError: String? = null
) {
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    class Failure<T>(error: String? = null, originalError: String? = null) :
        NetworkResponse<T>(error = error, originalError = originalError)

    class Loading<T>() : NetworkResponse<T>()
    class Idle<T>() : NetworkResponse<T>()
}
