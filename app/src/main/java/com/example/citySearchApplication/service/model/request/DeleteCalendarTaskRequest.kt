package com.example.citySearchApplication.service.model.request

import com.google.gson.annotations.SerializedName

data class DeleteCalendarTaskRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task_id")
    val taskId: Int
)
