package com.example.weatherapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.data.GovernoratesOfIraq
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.uti.toGoWeather
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy MM dd")
        val strDate: String = sdf.format(c.getTime())
        binding.nowDate.setText(strDate)
        makeRequestUsingOKHTTP(GovernoratesOfIraq.getCurrentGovernorate())
        binding.nextIcon.setOnClickListener {
            makeRequestUsingOKHTTP(GovernoratesOfIraq.getNextGovernorate())
        }
        binding.previousIcon.setOnClickListener {
            makeRequestUsingOKHTTP(GovernoratesOfIraq.getPreviousGovernorate())
        }

    }
    fun changeImage(temperature:String, imgBox:ImageView){
        val _temperature = temperature.substring(0, 2).toInt()
        if( _temperature >= 40)
            imgBox.setImageResource(R.drawable.sunny)
        else if (( _temperature >= 30)&&( _temperature >= 39))
            imgBox.setImageResource(R.drawable.warm)
        else if( _temperature < 30)
            imgBox.setImageResource(R.drawable.coldy)

    }
    private fun makeRequestUsingOKHTTP(city:String) {

        val request = Request.Builder().url("https://goweather.herokuapp.com/weather/$city").build()
        val response = client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG , "fail:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
//                Log.i(TAG , response.body?.string().toString())
                response.body?.string()?.let{ jsonString ->
                    val result = JSONObject(jsonString).toGoWeather()
                    val t = result.toString()
                    runOnUiThread{
                        binding.apply {
                            cityName.text = GovernoratesOfIraq.getCurrentGovernorate()
                            temprt.text = result.temperature.toString()
                            changeImage(temprt.text.toString(), statusImg)
                            WindStatusValue.text = result.wind.toString()
                            firstDayValue.text = result.forecast[0].temperature
                            changeImage(firstDayValue.text.toString(), firstDayImg)
                            secondDayValue.text = result.forecast[1].temperature
                            changeImage(secondDayValue.text.toString(), secondDayImg)
                            thirdDayValue.text = result.forecast[2].temperature

                        }
                    }
//                    Log.i(TAG , t)

                }
            }

        })



}
    object componian{
        const val TAG = "Mohammed"
    }
}