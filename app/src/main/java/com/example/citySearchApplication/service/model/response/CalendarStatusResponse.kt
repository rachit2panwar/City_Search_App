package com.example.citySearchApplication.service.model.response

import com.google.gson.annotations.SerializedName

data class CalendarStatusResponse(
    @SerializedName("status")
    val status: String? = null,

    var taskId : Int? = null
)