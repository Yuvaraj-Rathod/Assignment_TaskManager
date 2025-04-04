package com.assignment.taskmanager.api

import com.assignment.taskmanager.repository.TipsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object TipsModule {

    @Provides
    fun provideTipsApi(): TipsApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/b/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(TipsApiService::class.java)
    }

    @Provides
    fun provideTipsRepository(api: TipsApiService): TipsRepository {
        return TipsRepository(api)
    }
}
