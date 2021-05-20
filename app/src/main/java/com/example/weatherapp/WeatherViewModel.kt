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

    //Icon for the app
    private val _todayIcon = MutableLiveData<Int>()
    val todayIcon: LiveData<Int> = _todayIcon

    private val _tomorrow = MutableLiveData<Int>()
    val tomorrow: LiveData<Int> = _tomorrow

    private val _thedayafter = MutableLiveData<Int>()
    val thedayafter: LiveData<Int> = _thedayafter

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
                _date.value = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.KOREAN).format(Date(date*1000)).toString()

                _todayIcon.value = when (_weather.value){
                    "Rainy" -> R.drawable.humidity
                    "Sunny" -> R.drawable.sunrise
                    "Clouds" -> R.drawable.wind
                    else -> R.drawable.info
                }
                _tomorrow.value = when (_weather.value){
                    "Rainy" -> R.drawable.humidity
                    "Sunny" -> R.drawable.sunrise
                    "Clouds" -> R.drawable.wind
                    else -> R.drawable.info
                }
                _thedayafter.value = when (_weather.value){
                    "Rainy" -> R.drawable.humidity
                    "Sunny" -> R.drawable.sunrise
                    "Clouds" -> R.drawable.wind
                    else -> R.drawable.info
                }

            }
            catch (e : Exception){
                _weather.value = "Failure: ${e.message}"
            }
        }
    }

    init {
        getWeatherInfo()
    }
}