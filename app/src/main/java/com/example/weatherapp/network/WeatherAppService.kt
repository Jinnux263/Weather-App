package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.reflect.jvm.internal.impl.load.java.Constant

<<<<<<< HEAD
//Replace your City here and row 48
val CITY = {CITY}
//Replace your API here and row 48
val API = {YOUR API}
=======
val CITY = "dhaka,bd"
val API = ""
>>>>>>> 1d630587be83b4905700732d7e84bbac7ee5fb53

private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    /*
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * The Retrofit object with the Moshi converter.
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
     */

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    /**
     * Interface for the retrofit service
     */




    //Replace your API here
    interface WeatherAppService {
<<<<<<< HEAD
        @GET("weather?id={CITY_ID}&appid={YOUR API}")
=======
        @GET("weather?id=1566083&appid={API}")
>>>>>>> 1d630587be83b4905700732d7e84bbac7ee5fb53
        suspend fun getWeatherInfo() : String
    }

    object WeatherAPI {
        val retrofitService : WeatherAppService by lazy {
            retrofit.create(WeatherAppService :: class.java)}
    }
