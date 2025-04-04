package com.assignment.taskmanager.repository

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import android.os.Bundle
import com.assignment.taskmanager.room.Task
import com.assignment.taskmanager.room.TaskDao
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val analytics: FirebaseAnalytics,
    private val taskDao: TaskDao
) {
    // Firestore + Room Combined
    fun getTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(title: String) {
        val id = firestore.collection("tasks").document().id
        val task = Task(id = id, title = title)

        firestore.collection("tasks").document(id).set(task).await()
        taskDao.insertTask(task)

        val bundle = Bundle().apply { putString("task_title", title) }
        analytics.logEvent("task_added", bundle)
    }

    suspend fun updateTask(task: Task) {
        firestore.collection("tasks").document(task.id).set(task).await()
        taskDao.updateTask(task)

        val bundle = Bundle().apply { putString("task_title", task.title) }
        analytics.logEvent("task_completed", bundle)
    }

    suspend fun deleteTask(taskId: String) {
        firestore.collection("tasks").document(taskId).delete().await()
        taskDao.deleteById(taskId)

        val bundle = Bundle().apply { putString("task_id", taskId) }
        analytics.logEvent("task_deleted", bundle)
    }
}
