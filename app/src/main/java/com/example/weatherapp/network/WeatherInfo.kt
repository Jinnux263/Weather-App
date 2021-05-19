package com.example.weatherapp.network

import com.squareup.moshi.Json

data class WeatherInfo(

    @Json(name = "wind")
    val info: String
)