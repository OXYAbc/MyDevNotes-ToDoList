package com.example.mydevtodo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditTaskActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var topicSpinner: Spinner
    private lateinit var actionText: TextView
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        topicSpinner = findViewById(R.id.topicSpinner)
        actionText = findViewById<TextView>(R.id.action_text)


        actionText.setText("Edit Item")
        topicSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

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
