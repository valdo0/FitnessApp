package com.example.fitnessapp.ui.main.tabs.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp.R
import com.example.fitnessapp.data.model.RecipeItem
import com.example.fitnessapp.ui.components.CardListItem
import com.example.fitnessapp.ui.main.tabs.home.sampleRecipes
import com.example.fitnessapp.ui.main.tabs.recipes.sampleBreakfastRecipes
import com.example.fitnessapp.ui.navigation.AppScreens
import com.example.fitnessapp.ui.theme.FitnessAppTheme

// Uso de Función de Orden Superior  y filter
fun buscarRecetas(
    recetas:List<RecipeItem>,
    criterio: (RecipeItem) -> Boolean
): List<RecipeItem> {
    return recetas.filter(criterio)
}
// Funcion de extension
fun RecipeItem.cumpleBusqueda(query: String): Boolean {
    return this.title.contains(query, ignoreCase = true) ||
            (this.subtitle?.contains(query, ignoreCase = true) == true)
}
@Composable
fun SearchTabScreen (navController: NavHostController){
    var query by remember {mutableStateOf("")}
    val todasLasRecetas = sampleRecipes + sampleBreakfastRecipes
    // Uso de Lambda
    val resultados = buscarRecetas(todasLasRecetas) { receta ->
        receta.cumpleBusqueda(query)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {query = it},
            label = {Text("Buscar")},
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(resultados) { receta ->
                CardListItem(
                    title = receta.title,
                    subtitle = receta.subtitle,
                    imageUrl = receta.imageUrl,
                    placeholderResId = receta.placeholderResId,
                    onClick = {
                        navController.navigate(AppScreens.DetailScreen.route + "/${receta.id}")
                    }
                )
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun SearchTabScreenPreview(){
    FitnessAppTheme {
        SearchTabScreen( navController = NavHostController(LocalContext.current))
    }

}