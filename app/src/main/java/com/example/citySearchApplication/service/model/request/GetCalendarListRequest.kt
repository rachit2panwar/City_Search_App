package com.example.citySearchApplication.service.model.request

import com.google.gson.annotations.SerializedName

data class GetCalendarListRequest(
    @SerializedName("user_id")
    val userId: Int
)
