package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindDTO(
    @Json(name = "deg")val deg: Int,
    @Json(name = "gust")val gust: Double,
    @Json(name = "speed")val speed: Double
)