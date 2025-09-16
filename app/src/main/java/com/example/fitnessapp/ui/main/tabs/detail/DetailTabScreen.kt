package com.example.fitnessapp.ui.main.tabs.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.ui.main.tabs.home.sampleRecipes
import com.example.fitnessapp.ui.main.tabs.recipes.sampleBreakfastRecipes

@Composable
fun DetailScreen(recipeId:String?,navController: NavHostController){
    val receta = (sampleRecipes + sampleBreakfastRecipes).find {it.id == recipeId}
    receta?.let{
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Button(
                onClick = {navController.popBackStack()},
            ){
                Text("Volver" , style = MaterialTheme.typography.bodyLarge , color = MaterialTheme.colorScheme.onSecondary)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(it.title, style = MaterialTheme.typography.headlineMedium)
            Text(it.subtitle ?: "", style = MaterialTheme.typography.bodyLarge)

        }
    }?: run {
        Text("Receta no encontrada")
    }
}