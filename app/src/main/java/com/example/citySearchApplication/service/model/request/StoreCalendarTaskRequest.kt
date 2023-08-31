package com.example.citySearchApplication.service.model.request

import com.example.citySearchApplication.service.model.response.CityDetail
import com.google.gson.annotations.SerializedName

data class StoreCalendarTaskRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task")
    val task: CityDetail
)
