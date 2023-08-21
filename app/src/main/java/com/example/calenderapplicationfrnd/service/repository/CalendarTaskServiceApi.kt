package com.example.calenderapplicationfrnd.service.repository

import com.example.calenderapplicationfrnd.service.model.request.DeleteCalendarTaskRequest
import com.example.calenderapplicationfrnd.service.model.request.GetCalendarListRequest
import com.example.calenderapplicationfrnd.service.model.request.StoreCalendarTaskRequest
import com.example.calenderapplicationfrnd.service.model.response.GetCalendarResponse
import com.example.calenderapplicationfrnd.service.model.response.CalendarStatusResponse
import retrofit2.Response
import retrofit2.http.*

interface CalendarTaskServiceApi {

    @POST("api/getCalendarTaskList")
    suspend fun getCalendarTaskList(@Body getCalendarListRequest: GetCalendarListRequest): Response<GetCalendarResponse>

    @POST("api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body storeCalendarTaskRequest: StoreCalendarTaskRequest): Response<CalendarStatusResponse>

    @POST("api/deleteCalendarTask")
    suspend fun deleteCalendarTask(@Body deleteCalendarTaskRequest: DeleteCalendarTaskRequest): Response<CalendarStatusResponse>

}