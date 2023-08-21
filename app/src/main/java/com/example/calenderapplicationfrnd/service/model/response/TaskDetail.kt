package com.example.calenderapplicationfrnd.service.model.response

import com.google.gson.annotations.SerializedName

data class TaskDetail(
    @SerializedName("title")
    val title : String? = null,
    @SerializedName("description")
    val description : String? = null,
    @SerializedName("date")
    val date: String? = null
)
