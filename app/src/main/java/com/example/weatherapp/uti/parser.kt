package com.example.weatherapp.uti

import com.example.weatherapp.data.Forecast
import com.example.weatherapp.data.GoWeather
import org.json.JSONObject

fun JSONObject.toGoWeather():GoWeather{
    val temperature = this.getString("temperature")
    val wind = this.getString("wind")
    val description = this.getString("description")
    val forecast = this.getJSONArray("forecast")
    val listOfForecast = mutableListOf<Forecast>()
    for (i in 0 until forecast.length()){
        val forecastJSONObject = forecast[i] as JSONObject
        val day = forecastJSONObject.getString("day")
        val temperature = forecastJSONObject.getString("temperature")
        val wind = forecastJSONObject.getString("wind")
        listOfForecast.add(Forecast(day, temperature, wind))
    }
    return GoWeather(temperature, wind, description, listOfForecast)
}