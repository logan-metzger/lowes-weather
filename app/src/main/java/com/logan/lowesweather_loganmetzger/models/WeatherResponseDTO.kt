package com.logan.lowesweather_loganmetzger.models

import com.squareup.moshi.Json

data class WeatherResponseDTO(
    @Json(name = "clouds")val clouds: CloudsDTO,
    @Json(name = "dt")val dt: Int,
    @Json(name = "dt_txt")val dtTxt: String,
    @Json(name = "main")val main: MainDTO,
    @Json(name = "pop")val pop: Int,
    @Json(name = "sys")val sys: SysDTO,
    @Json(name = "visibility")val visibility: Int,
    @Json(name = "weather")val weather: List<WeatherDTO>,
    @Json(name = "wind")val wind: WindDTO
)