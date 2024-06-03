package com.example.amphibiansbyjb.data

import com.example.amphibiansbyjb.model.AmphibiansModel
import com.example.amphibiansbyjb.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansModel>
}

class LocalAmphibiansRepository : AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansModel> {
        return listOf<AmphibiansModel>();
    }
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansModel> = amphibiansApiService.getAmphibians()
}
