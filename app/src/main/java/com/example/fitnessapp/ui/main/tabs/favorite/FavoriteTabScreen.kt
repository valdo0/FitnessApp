package com.example.fitnessapp.ui.main.tabs.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.components.CardListItem

import com.example.fitnessapp.ui.main.tabs.home.sampleRecipes
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FavoriteTabScreen (modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(
            items = sampleFavouritesRecipes,
            key = { recipe -> recipe.id }

        ){
            recipeItem ->
            CardListItem(
                title = recipeItem.title,
                subtitle = recipeItem.subtitle,
                imageUrl = recipeItem.imageUrl,
                placeholderResId = recipeItem.placeholderResId,
                onClick = {
                    println("Clicked on: ${recipeItem.title}")
                    })
        }
     }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteTabScreenPreview() {
    FitnessAppTheme {
        FavoriteTabScreen()
    }
}
