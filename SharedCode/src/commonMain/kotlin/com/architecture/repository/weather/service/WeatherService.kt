package com.architecture.repository.weather.service

import com.architecture.repository.weather.model.WeatherModel

//interface WeatherRemoteService {
//    @GET("/data/2.5/forecast/daily")
//    suspend fun getWeather(
//        @Query(value = "q") city: String,
//        @Query(value = "cnt") numberOfDate: String,
//        @Query(value = "appid") appId: String,
//        @Query(value = "units") unit: String
//    ): WeatherModel
//}

interface WeatherService {
    suspend fun getWeather(
        city: String,
        numberOfDate: String,
        appId: String,
        unit: String
    ): WeatherModel
}