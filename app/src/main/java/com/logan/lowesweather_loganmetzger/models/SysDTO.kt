package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json

data class SysDTO(
    @Json(name = "pod")val pod: String
)