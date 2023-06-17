package com.example.mydevtodo

object TaskManager {
    val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }
}
