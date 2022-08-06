package com.example.weatherapp.data

object GovernoratesOfIraq {
    val governoratesList = mutableListOf<String>("Babylon","Kirkuk",  "Mosul", "Maysan", "Basra", "Diyala", "Erbil", "Diyala", "Duhok", "Karbala", "Najaf", "Nineveh", "Sulaymaniyah", "Halabja", "Wasit", "Anbar");

    private var governorateIndex =0

    fun getCurrentGovernorate() = governoratesList[governorateIndex]
    fun getNextGovernorate(): String {
        governorateIndex++
        if(governorateIndex ==governoratesList.size){
            governorateIndex = 0
        }
        return governoratesList[governorateIndex]
    }
    fun getPreviousGovernorate(): String {
        governorateIndex--
        if(governorateIndex ==-1){
            governorateIndex = governoratesList.size-1
        }
        return governoratesList[governorateIndex]
    }
}