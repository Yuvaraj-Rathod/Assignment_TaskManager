package com.assignment.taskmanager.repository

import com.assignment.taskmanager.api.RetrofitInstance
import com.assignment.taskmanager.api.TipsApiService
import com.assignment.taskmanager.api.model.Tip
import javax.inject.Inject

class TipsRepository @Inject constructor(
    private val apiService: TipsApiService
) {
    suspend fun fetchTips(): List<Tip> {
        return apiService.getTips().tweets
    }
}
