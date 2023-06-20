package com.example.myapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mydevtodo.EditTaskActivity
import com.example.mydevtodo.MainActivity
import com.example.mydevtodo.R
import com.example.mydevtodo.Task

class TaskAdapter(private val tasks: MutableList<Task>, private val mainActivity: MainActivity) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.task_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mDescriptionView: TextView? = null
        private var mTopicView: TextView? = null

        init {
            mTitleView = itemView.findViewById(R.id.title)
            mDescriptionView = itemView.findViewById(R.id.description)
            mTopicView = itemView.findViewById(R.id.topic)
        }

        fun bind(task: Task) {
            mTitleView?.text = task.title
            mDescriptionView?.text = task.description
            mTopicView?.text = task.topic
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.bind(task)

        holder.itemView.findViewById<TextView>(R.id.topic).apply {

            when(task.topic){
                "Notes" -> setTextColor(ContextCompat.getColor(context, R.color.white))
                "Frontend" -> setTextColor(ContextCompat.getColor(context, R.color.green))
                "Backend" -> setTextColor(ContextCompat.getColor(context, R.color.yellow))
                "Other" -> setTextColor(ContextCompat.getColor(context, R.color.teal_200))
            }
        }

        holder.itemView.findViewById<Button>(R.id.edit_button).setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditTaskActivity::class.java).apply {
                putExtra("taskId", position)
            }
            context.startActivity(intent)
        }

        holder.itemView.findViewById<CheckBox>(R.id.task_done_checkbox).apply {
            isChecked = task.isDone
            if (task.topic == "Notes") {
                visibility = View.GONE
            }
            setOnClickListener {
                task.isDone = isChecked
                mainActivity.updateProgressBars()
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
