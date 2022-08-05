package com.example.weatherapp.data

data class GoWeather (
    val temperature: String,
    val wind: String,
    val description: String,
    val forecast: List<Forecast>
        )