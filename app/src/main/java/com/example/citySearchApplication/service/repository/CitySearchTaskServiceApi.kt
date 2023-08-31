package com.example.citySearchApplication.service.repository

import com.example.citySearchApplication.service.model.response.GetSearchCityResponse
import retrofit2.Response
import retrofit2.http.*

interface CitySearchTaskServiceApi {

    @GET("searchJSON")
    suspend fun getCitySearchList(@Query("name_startsWith", encoded = true) nameStart: String, @Query("maxRows") maxRows: Long, @Query("username") username: String): Response<GetSearchCityResponse>
//
//    @POST("api/storeCalendarTask")
//    suspend fun storeCalendarTask(@Body storeCalendarTaskRequest: StoreCalendarTaskRequest): Response<CalendarStatusResponse>
//
//    @POST("api/deleteCalendarTask")
//    suspend fun deleteCalendarTask(@Body deleteCalendarTaskRequest: DeleteCalendarTaskRequest): Response<CalendarStatusResponse>

}