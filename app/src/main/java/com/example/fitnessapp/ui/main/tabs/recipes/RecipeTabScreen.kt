package com.example.fitnessapp.ui.main.tabs.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.components.CardRecite
import com.example.fitnessapp.ui.theme.FitnessAppTheme
@Composable
fun RecipeTabScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        item{
            Text(
                text = "Desayunos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleBreakfastRecipes, key = { recipe -> recipe.id }) { recipeItem ->
                    CardRecite(
                        modifier = Modifier.width(220.dp).height(400.dp),
                        title = recipeItem.title,
                        subtitle = recipeItem.subtitle,
                        imageUrl = recipeItem.imageUrl,
                        placeholderResId = recipeItem.placeholderResId,
                        onClick = {
                            println("Clicked on: ${recipeItem.title}")
                        }
                    )
                }
            }
        }
        item{
            Spacer(modifier = Modifier.height(16.dp))
        }

        item{
            Text(
                text = "Almuerzos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleRecipes, key = { recipe -> recipe.id }) { recipeItem ->
                    CardRecite(
                        modifier = Modifier.width(220.dp).height(400.dp),
                        title = recipeItem.title,
                        subtitle = recipeItem.subtitle,
                        imageUrl = recipeItem.imageUrl,
                        placeholderResId = recipeItem.placeholderResId,
                        onClick = {
                            println("Clicked on: ${recipeItem.title}")
                        }
                    )
                }
            }
        }



    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun HomeTabScreenPreview() {
    FitnessAppTheme {
        RecipeTabScreen()
    }
}
