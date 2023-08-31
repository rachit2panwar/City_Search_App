package com.example.citySearchApplication.service

import com.example.citySearchApplication.service.repository.CitySearchTaskServiceApi
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
                .baseUrl("https://secure.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        }
        val api by lazy {
            retrofit.create(CitySearchTaskServiceApi::class.java)
        }
    }
}