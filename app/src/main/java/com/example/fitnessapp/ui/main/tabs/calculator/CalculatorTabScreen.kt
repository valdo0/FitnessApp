package com.example.fitnessapp.ui.main.tabs.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessapp.data.model.ActivityLevel
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTabScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Calculadora de Calorías",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = viewModel.age,
            onValueChange = { viewModel.age = it },
            label = { Text("Edad (años)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.weight,
            onValueChange = { viewModel.weight = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.height,
            onValueChange = { viewModel.height = it },
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FilterChip(
                selected = viewModel.gender == "M",
                onClick = { viewModel.gender = "M" },
                label = { Text("Hombre") }
            )
            FilterChip(
                selected = viewModel.gender == "F",
                onClick = { viewModel.gender = "F" },
                label = { Text("Mujer") }
            )
        }

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = viewModel.activityLevel.display,
                onValueChange = {},
                label = { Text("Nivel de actividad") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ActivityLevel.entries.forEach { level ->
                    DropdownMenuItem(
                        text = { Text(level.display) },
                        onClick = {
                            viewModel.activityLevel = level
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                viewModel.calculateCalories { bmr, tdee ->
                    println("Resultados: BMR=$bmr, TDEE=$tdee")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Calcular",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }
        viewModel.bmr?.let {
            Text(
                text = "BMR (Metabolismo basal): %.0f kcal/día".format(it),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        viewModel.tdee?.let {
            Text(
                text = "TDEE (Gasto energético total): %.0f kcal/día".format(it),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FavoriteTabScreenPreview() {
    FitnessAppTheme {
        CalculatorTabScreen()
    }
}
