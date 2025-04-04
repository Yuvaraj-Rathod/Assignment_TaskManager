package com.assignment.taskmanager.repository

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import android.os.Bundle
import com.assignment.taskmanager.model.Task
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val analytics: FirebaseAnalytics
) {
    private val tasksCollection = firestore.collection("tasks")

    // Fetch tasks
    fun getTasks(): Flow<List<Task>> = callbackFlow {
        val listener = tasksCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }
                val tasks = snapshot?.toObjects(Task::class.java) ?: emptyList()
                trySend(tasks)
            }
        awaitClose { listener.remove() }
    }

    //Add a task
    suspend fun addTask(title: String) {
        val newTaskRef = tasksCollection.document() // Auto-generates unique Firestore ID
        val task = Task(id = newTaskRef.id, title = title) // Use this ID for the `id` field
        newTaskRef.set(task).await() // Save task with matching ID
    }

    // Update a task
    suspend fun updateTask(task: Task) {
        tasksCollection.document(task.id).set(task).await()
        val bundle = Bundle().apply { putString("task_title", task.title) }
        analytics.logEvent("task_completed", bundle)
    }

    //Delete a task
    suspend fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete().await()
    }
}