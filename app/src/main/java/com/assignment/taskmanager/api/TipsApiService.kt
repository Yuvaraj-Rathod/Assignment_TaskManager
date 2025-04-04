package com.assignment.taskmanager.api

import com.assignment.taskmanager.api.model.TipResponse
import retrofit2.http.GET


interface TipsApiService {
    @GET("66f5687ce41b4d34e43839b0?meta=false")
    suspend fun getTips(): TipResponse
}
