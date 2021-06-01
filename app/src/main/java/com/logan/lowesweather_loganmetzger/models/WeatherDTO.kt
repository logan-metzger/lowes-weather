package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDTO(
    @Json(name = "description")val description: String,
    @Json(name = "icon")val icon: String,
    @Json(name = "id")val id: Int,
    @Json(name = "main")val main: String
)