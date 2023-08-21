package com.example.calenderapplicationfrnd.service.model.response

import com.google.gson.annotations.SerializedName

data class GetCalendarResponse(
    @SerializedName("tasks")
    val tasks : ArrayList<Tasks> = arrayListOf()
)