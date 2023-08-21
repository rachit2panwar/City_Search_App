package com.example.calenderapplicationfrnd.service

import com.example.calenderapplicationfrnd.service.repository.CalendarTaskServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitInstance {
    companion object {
        val retrofit by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            Retrofit.Builder()
                .baseUrl("http://dev.frndapp.in:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        }
        val api by lazy {
            retrofit.create(CalendarTaskServiceApi::class.java)
        }
    }
}