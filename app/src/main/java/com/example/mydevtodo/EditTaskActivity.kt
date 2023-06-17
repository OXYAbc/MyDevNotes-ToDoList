package com.example.mydevtodo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class EditTaskActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var topicSpinner: Spinner
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        topicSpinner= findViewById(R.id.topicSpinner)

        taskId = intent.getIntExtra("taskId", -1)
        val task = TaskManager.tasks.getOrNull(taskId)

        if (task != null) {
            titleEditText.setText(task.title)
            descriptionEditText.setText(task.description)
            val topicOptions = resources.getStringArray(R.array.topic_options)
            val topicIndex = topicOptions.indexOf(task.topic)
            if (topicIndex != -1) {
                topicSpinner.setSelection(topicIndex)
            }
        }

        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val editedTitle = titleEditText.text.toString()
            val editedDescription = descriptionEditText.text.toString()
            val editedTopic = topicSpinner.selectedItem.toString()

            if (task != null) {
                task.title = editedTitle
                task.description = editedDescription
                task.topic = editedTopic
            }

            finish()
        }
    }
}
