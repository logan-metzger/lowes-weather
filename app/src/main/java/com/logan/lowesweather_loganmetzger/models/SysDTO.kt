package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SysDTO(
    @Json(name = "pod")val pod: String
)