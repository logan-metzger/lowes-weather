package com.logan.lowesweather_loganmetzger.repositories

import com.logan.lowesweather_loganmetzger.models.WeatherResponseDTO
import com.logan.lowesweather_loganmetzger.remote.WeatherManager
import retrofit2.Response


class WeatherRepository {
    suspend fun getWeather(city: String): Response<WeatherResponseDTO> {
        return WeatherManager().getWeather(city)
    }
}