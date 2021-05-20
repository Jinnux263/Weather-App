package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val ViewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val View = binding.root
        setSupportActionBar(binding.toolbar)
        setContentView(View)

        ViewModel.weather.observe(this, Observer { t -> binding.weather.text = t })
        ViewModel.city.observe(this, Observer { t -> binding.city.text = t })
        ViewModel.temp.observe(this, Observer { t -> binding.temperature.text = t; binding.tempMinMaxToday.text = "$t / $t" })
        ViewModel.date.observe(this, Observer { t -> binding.date.text = t })
        ViewModel.todayIcon.observe(this, {t -> binding.icToday.setImageResource(t)})
        ViewModel.tomorrow.observe(this, {t -> binding.icTomorrow.setImageResource(t)})
        ViewModel.thedayafter.observe(this, {t -> binding.icDayafter.setImageResource(t)})

        binding.btnCheck.setOnClickListener(){
            ViewModel.getWeatherInfo()
            /*
            binding.weather.text = ViewModel.weather.value
            binding.city.text = ViewModel.city.value
            binding.temperature.text = ViewModel.temp.value
            binding.date.text = ViewModel.date.value
             */
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}