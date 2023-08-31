package com.example.citySearchApplication.service.repository

import com.example.citySearchApplication.service.RetroFitInstance

class CitySearchRepository(){
    suspend fun getSearchCityList(nameStart: String, maxRows: Long, username: String) =
        RetroFitInstance.api.getCitySearchList(nameStart,maxRows,username)
}