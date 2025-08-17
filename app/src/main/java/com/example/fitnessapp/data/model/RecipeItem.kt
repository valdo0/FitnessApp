package com.example.fitnessapp.data.model

import androidx.annotation.DrawableRes

data class RecipeItem(
    val id: String,
    val title: String,
    val subtitle: String?,
    val imageUrl: String?,
    @DrawableRes val placeholderResId: Int?
)
