package com.example.weatherapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherAPI
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeatherViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response
    init {
        getWeatherInfo()
    }

    fun getWeatherInfo() {
        viewModelScope.launch {
            try {
                //val jsObject = WeatherAPI.retrofitService.getWeatherInfo()
                //_response.value = jsObject.getJSONObject("wind").toString()
                _response.value = WeatherAPI.retrofitService.getWeatherInfo()
            }
            catch (e : Exception){
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}