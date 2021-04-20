package com.uzlahpri.weatherappp.network

import com.uzlahpri.weatherappp.model.DailyResponse
import com.uzlahpri.weatherappp.model.ForecastResponse
import com.uzlahpri.weatherappp.model.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?")
    fun getWeatherByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String,
    ): Single<WeatherResponse>

    @GET("forecast?")
    fun getForeCastByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String,
    ): Single<ForecastResponse>

    @GET("find?")
    fun getCityDailyWeatherByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("cnt") cnt: String,
        @Query("units") units: String,
    ): Single<DailyResponse>
}