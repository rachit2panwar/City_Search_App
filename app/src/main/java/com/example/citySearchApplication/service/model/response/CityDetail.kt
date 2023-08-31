package com.example.citySearchApplication.service.model.response

import com.google.gson.annotations.SerializedName

data class CityDetail(
    @SerializedName("toponymName")
    val cityName : String? = null,
    @SerializedName("adminName1")
    val stateName : String? = null,
    @SerializedName("countryName")
    val countryName: String? = null
)
