package com.example.calenderapplicationfrnd.view.callback

import com.example.calenderapplicationfrnd.view.adapter.TaskItemViews

interface TaskItemClickListener {
    fun onTaskItemClicked(item: TaskItemViews.TaskItem)
}