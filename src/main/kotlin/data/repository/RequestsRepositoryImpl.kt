package data.repository

import data.model.SupaRequestModel
import data.network.NetworkResponse
import data.network.SupaProvider
import domain.repository.RequestsRepository
import io.github.jan.supabase.postgrest.from
import io.ktor.client.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestsRepositoryImpl(
    private val supaProvider: SupaProvider
) : RequestsRepository {
    override suspend fun getAllRequest(): NetworkResponse<List<SupaRequestModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val list = supaProvider.getSupabase().from("Requests").select().decodeList<SupaRequestModel>()
                NetworkResponse.Success(list)
            } catch (e: SocketTimeoutException) {
                NetworkResponse.Failure(e.message)
            }catch (e: Exception) {
                NetworkResponse.Failure(e.message)
            }
        }
    }
}