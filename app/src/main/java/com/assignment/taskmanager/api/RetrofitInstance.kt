package com.assignment.taskmanager.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object RetrofitInstance {
    private const val BASE_URL = "https://api.jsonbin.io/v3/b/"

    private val client = OkHttpClient.Builder().build()

    val api: TipsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TipsApiService::class.java)
    }
}
