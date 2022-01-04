package com.example.weatherapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

/*const val CITY = "dhaka,bd"
const val API = "8e81593bdb9363e310503094470b8bb5"*/

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    /**
     * Interface for the retrofit service
     */
    interface WeatherAppService {
        @GET("weather?id=1566083&appid=8e81593bdb9363e310503094470b8bb5")
        suspend fun getWeatherInfo() : String
    }

    object WeatherAPI {
        val retrofitService : WeatherAppService by lazy {
            retrofit.create(WeatherAppService :: class.java)}
    }
