package com.example.mydevtodo

data class Task(
    var title: String,
    var description: String,
    var topic: String,
    var isDone: Boolean = false
)
