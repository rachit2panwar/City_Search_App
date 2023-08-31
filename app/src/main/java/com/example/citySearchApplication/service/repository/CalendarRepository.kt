package com.example.citySearchApplication.service.repository

import com.example.citySearchApplication.service.RetroFitInstance

class CalendarRepository(){
    suspend fun getSearchCityList(nameStart: String, maxRows: Long, username: String) =
        RetroFitInstance.api.getCitySearchList(nameStart,maxRows,username)

//    suspend fun storeCalendarTask(storeCalendarTaskRequest: StoreCalendarTaskRequest) =
//        RetroFitInstance.api.storeCalendarTask(storeCalendarTaskRequest)
//
//    suspend fun deleteCalendarTask(deleteCalendarTaskRequest: DeleteCalendarTaskRequest) =
//        RetroFitInstance.api.deleteCalendarTask(deleteCalendarTaskRequest)
}