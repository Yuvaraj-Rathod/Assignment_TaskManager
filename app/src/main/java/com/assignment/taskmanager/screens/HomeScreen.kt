package com.assignment.taskmanager.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import com.assignment.taskmanager.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task,
                    onToggleComplete = { viewModel.toggleTaskCompletion(task) },
                    onDelete = { viewModel.deleteTask(task.id) }
                )
            }
        }

        FloatingActionButton(
            onClick = { viewModel.addTask("New Task") },
            modifier = Modifier.align(Alignment.End).padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task")
        }
    }
}
