package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloudsDTO(
    @Json(name = "all")val all: Int
)