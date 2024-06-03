package com.example.amphibiansbyjb.network

import com.example.amphibiansbyjb.model.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("/amphibians")
    suspend fun getAmphibians(): List<AmphibiansModel>
}
