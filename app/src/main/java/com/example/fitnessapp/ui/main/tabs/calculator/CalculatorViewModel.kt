package com.example.fitnessapp.ui.main.tabs.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.data.model.ActivityLevel
class CalculatorViewModel : ViewModel() {

    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var gender by mutableStateOf("M")
    var activityLevel by mutableStateOf(ActivityLevel.Sedentario)

    var bmr by mutableStateOf<Double?>(null)
    var tdee by mutableStateOf<Double?>(null)
// Uso de funcion inline y de try Catch
    inline fun calculateCalories(onResult:(bmr:Double,tdee:Double) -> Unit) {

        try {
            val ageInt = age.toInt()
            val weightKg = weight.toDouble()
            val heightCm = height.toDouble()

            val calculatedBmr = if (gender == "M") {
                88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * ageInt)
            }else{
                447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * ageInt)

            }
            val calculatedTdee = calculatedBmr * activityLevel.factor
            bmr = calculatedBmr
            tdee = calculatedTdee

            onResult(calculatedBmr,calculatedTdee)


        } catch(e: NumberFormatException) {
            println("Error: ${e.message}")
            bmr = null
            tdee = null

        }

    }

}