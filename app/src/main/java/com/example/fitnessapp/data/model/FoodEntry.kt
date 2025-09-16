package com.example.fitnessapp.data.model


data class FoodEntry(
    val name: String,
    val calories: Int,
    val timestamp: Long = System.currentTimeMillis()
)
