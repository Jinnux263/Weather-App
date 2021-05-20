package com.example.weatherapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherAPI
import com.example.weatherapp.network.WeatherInfo
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {
    private val _temp = MutableLiveData<String>()
    val temp: LiveData<String> = _temp
    private val _weather = MutableLiveData<String>()
    val weather: LiveData<String> = _weather
    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        getWeatherInfo()
    }

    fun getWeatherInfo() {
        viewModelScope.launch {
            try {
                val jsObject = JSONObject(WeatherAPI.retrofitService.getWeatherInfo())
                val mainObj = jsObject.getJSONObject("main")
                val nameObj = jsObject.getString("name").toString()
                val weatherObj = jsObject.getJSONArray("weather").getJSONObject(0)
                val date = jsObject.getLong("dt")

                _temp.value = (mainObj.getInt("temp") - 273).toString() + "°C"
                _city.value = nameObj
                _weather.value = weatherObj.getString("main").toString()
                _date.value = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.KOREAN).format(Date(date*1000)).toString()

            }
            catch (e : Exception){
                _weather.value = "Failure: ${e.message}"
            }
        }
    }
}