package com.assignment.taskmanager.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.taskmanager.api.model.Tip
import com.assignment.taskmanager.repository.TipsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    private val repository: TipsRepository
) : ViewModel() {

    var tips by mutableStateOf<List<Tip>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        fetchTips()
    }

    private fun fetchTips() {
        viewModelScope.launch {
            try {
                isLoading = true
                tips = repository.fetchTips()
            } catch (e: Exception) {
                Log.e("TipsViewModel", "Error fetching tips", e)
            } finally {
                isLoading = false
            }
        }
    }
}
