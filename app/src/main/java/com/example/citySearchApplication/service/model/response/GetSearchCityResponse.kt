package com.example.citySearchApplication.service.model.response

import com.google.gson.annotations.SerializedName

data class GetSearchCityResponse(
    @SerializedName("geonames")
    val geoNames : ArrayList<CityDetail> = arrayListOf(),
    @SerializedName("totalResultsCount")
    val totalResultsCount : Long? = null
)