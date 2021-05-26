package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json

data class CloudsDTO(
    @Json(name = "all")val all: Int
)