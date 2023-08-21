package com.example.calenderapplicationfrnd.service.repository

import com.example.calenderapplicationfrnd.service.RetroFitInstance
import com.example.calenderapplicationfrnd.service.model.request.DeleteCalendarTaskRequest
import com.example.calenderapplicationfrnd.service.model.request.GetCalendarListRequest
import com.example.calenderapplicationfrnd.service.model.request.StoreCalendarTaskRequest

class CalendarRepository(){
    suspend fun getCalendarTask(calendarListRequest: GetCalendarListRequest) =
        RetroFitInstance.api.getCalendarTaskList(calendarListRequest)

    suspend fun storeCalendarTask(storeCalendarTaskRequest: StoreCalendarTaskRequest) =
        RetroFitInstance.api.storeCalendarTask(storeCalendarTaskRequest)

    suspend fun deleteCalendarTask(deleteCalendarTaskRequest: DeleteCalendarTaskRequest) =
        RetroFitInstance.api.deleteCalendarTask(deleteCalendarTaskRequest)
}