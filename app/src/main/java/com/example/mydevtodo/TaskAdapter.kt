package com.example.myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydevtodo.R
import com.example.mydevtodo.Task

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
