package com.example.fitnessapp.ui.main.tabs.foodTracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun FoodTrackerScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodTrackerViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Registro de comidas",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = viewModel.foodName,
            onValueChange = { viewModel.foodName = it },
            label = { Text("Comida / Alimento") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.foodCalories,
            onValueChange = { viewModel.foodCalories = it },
            label = { Text("Calorías") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.addFood() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar" ,color = MaterialTheme.colorScheme.onSecondary,fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        }

        Text(
            text = "Total del día: ${viewModel.totalCalories} kcal",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.foodList) { entry ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(entry.name, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                formatDate(entry.timestamp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("${entry.calories} kcal")
                            TextButton(onClick = { viewModel.removeFood(entry) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(Date(millis))
}
@Preview(showBackground = true)
@Composable
fun FoodTrackerScreenPreview() {
    FitnessAppTheme {
        FoodTrackerScreen()
    }
}