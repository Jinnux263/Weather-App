package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val ViewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val View = binding.root
        setSupportActionBar(binding.toolbar)
        setContentView(View)

        binding.btnCheck.setOnClickListener(){
            ViewModel.getWeatherInfo()
            binding.weather.text = ViewModel.weather.value
            binding.city.text = ViewModel.city.value
            binding.temperature.text = ViewModel.temp.value
            binding.date.text = ViewModel.date.value
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}