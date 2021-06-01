package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseDTO(
    @Json(name = "cnt")val cnt: Int?,
    @Json(name = "cod")val cod: String?,
    @Json(name = "list")val list: List<HourlyResponseDTO>,
    @Json(name = "message")val message: Int?
)