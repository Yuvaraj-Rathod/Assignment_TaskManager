package com.assignment.taskmanager.model

data class Task(
    val id : String = "",
    val title : String = "",
    val isCompleted : Boolean = false,
    val createdAt : Long = System.currentTimeMillis()
)
