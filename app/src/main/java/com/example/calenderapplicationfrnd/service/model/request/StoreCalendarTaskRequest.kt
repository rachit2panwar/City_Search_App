package com.example.calenderapplicationfrnd.service.model.request

import com.example.calenderapplicationfrnd.service.model.response.TaskDetail
import com.google.gson.annotations.SerializedName

data class StoreCalendarTaskRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task")
    val task: TaskDetail
)
