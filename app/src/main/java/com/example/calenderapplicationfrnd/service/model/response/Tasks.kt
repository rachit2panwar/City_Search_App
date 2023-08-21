package com.example.calenderapplicationfrnd.service.model.response

import com.google.gson.annotations.SerializedName

data class Tasks(
    @SerializedName("task_id")
    val taskId : Int? = null,
    @SerializedName("task_detail")
    val taskDetail : TaskDetail? = null
)
