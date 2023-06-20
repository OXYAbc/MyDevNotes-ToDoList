package com.example.mydevtodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapp.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var frontendProgressBar: ProgressBar
    private lateinit var backendProgressBar: ProgressBar
    private lateinit var otherProgressBar: ProgressBar

    private lateinit var progressFEText: TextView
    private lateinit var progressBEText: TextView
    private lateinit var progressOtherText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressFEText = findViewById(R.id.progressFE_text)
        progressBEText = findViewById(R.id.progressBE_text)
        progressOtherText = findViewById(R.id.progressOther_text)

        frontendProgressBar = findViewById(R.id.frontendProgressBar)
        backendProgressBar = findViewById(R.id.backendProgressBar)
        otherProgressBar = findViewById(R.id.otherProgressBar)

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView)
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)

        updateProgressBars()

        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateProgressBars() {
        val tasks = TaskManager.tasks
        val totalTasks = tasks.size.toFloat()

        val frontendTasks = tasks.count { it.topic == "Frontend" }
        val frontendTasksDone = tasks.count { it.topic == "Frontend" && it.isDone}
        val backendTasks = tasks.count { it.topic == "Backend" }
        val backendTasksDone = tasks.count { it.topic == "Backend" && it.isDone }
        val otherTasks = tasks.count { it.topic == "Other" }
        val otherTasksDone = tasks.count { it.topic == "Other" && it.isDone}

        val frontendProgress = calculateProgress(frontendTasks, frontendTasksDone)
        val backendProgress = calculateProgress(backendTasks, backendTasksDone)
        val otherProgress = calculateProgress(otherTasks, otherTasksDone)

        frontendProgressBar.progress = (frontendProgress * 100).toInt()
        progressFEText.setText("Frontend\n"+ frontendTasksDone + "/" + frontendTasks)
        backendProgressBar.progress = (backendProgress * 100).toInt()
        progressBEText.setText("Backend\n" + backendTasksDone + "/" + backendTasks)
        otherProgressBar.progress = (otherProgress * 100).toInt()
        progressOtherText.setText("Other\n" + otherTasksDone + "/" + otherTasks)
    }

    private fun calculateProgress(totalTasks: Int, tasksDone: Int): Float {
        return if (totalTasks > 0) {
            tasksDone.toFloat() / totalTasks
        } else {
            0f
        }
    }

    override fun onResume() {
        super.onResume()
        tasksRecyclerView.adapter = TaskAdapter(TaskManager.tasks, this)
        updateProgressBars()
    }
}

