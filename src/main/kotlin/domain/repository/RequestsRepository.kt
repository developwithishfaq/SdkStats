package domain.repository

import data.model.SupaRequestModel
import data.network.NetworkResponse

interface RequestsRepository {
    suspend fun getAllRequest(): NetworkResponse<List<SupaRequestModel>>
}