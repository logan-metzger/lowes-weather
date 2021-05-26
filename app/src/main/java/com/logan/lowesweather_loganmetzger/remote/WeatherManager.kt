package com.logan.lowesweather_loganmetzger.remote

import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO
import com.logan.lowesweather_loganmetzger.utils.Constants.Companion.API_KEY
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(city: String) =
        service.getWeather(city, API_KEY)

    interface WeatherService {
        @GET("/data/2.5/forecast")
        suspend fun getWeather(
            @Query("city") city: String,
            @Query("api_key") apiKey: String
        ): Response<WeatherResponseDTO>
    }
}