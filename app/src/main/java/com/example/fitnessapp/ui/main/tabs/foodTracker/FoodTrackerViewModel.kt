package com.example.fitnessapp.ui.main.tabs.foodTracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.data.model.FoodEntry

class FoodTrackerViewModel : ViewModel() {
    var foodName by mutableStateOf("")
    var foodCalories by mutableStateOf("")

    var foodList = mutableStateListOf<FoodEntry>()
        private set

    val totalCalories: Int
        get() = foodList.sumOf { it.calories }

    fun addFood() {
        val caloriesInt = foodCalories.toIntOrNull()
        if (foodName.isNotBlank() && caloriesInt != null) {
            foodList.add(FoodEntry(foodName, caloriesInt))
            foodName = ""
            foodCalories = ""
        }
    }

    fun removeFood(entry: FoodEntry) {
        foodList.remove(entry)
    }
}