package com.example.mydevtodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Spinner

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val topicSpinner: Spinner = findViewById(R.id.topicSpinner)
        val saveButton = findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val topic = topicSpinner.selectedItem.toString()
            val task = Task(title, description, topic)

            TaskManager.addTask(task)
            finish()
        }
    }
}
