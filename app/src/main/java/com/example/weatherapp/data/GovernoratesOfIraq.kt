package com.example.weatherapp.data

object GovernoratesOfIraq {
    val governoratesList = mutableListOf<String>("Babylon","Baghdad" , "Basra", "Diyala", "Diyala", "Karbala", "Najaf");

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