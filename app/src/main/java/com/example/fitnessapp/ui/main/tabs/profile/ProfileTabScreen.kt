package com.example.fitnessapp.ui.main.tabs.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R // Asegúrate de tener un placeholder si lo usas
import com.example.fitnessapp.ui.components.CircularProfileImage // Importa tu componente
import com.example.fitnessapp.ui.theme.FitnessAppTheme // Para el Preview con tu tema

@Composable
fun ProfileTabScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CircularProfileImage(
            placeholderResId = R.drawable.profile_placeholder,
            imageSize = 120.dp,
            borderWidth = 3.dp,
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nombre Apellido",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )


        Text(
            text = "usuario@example.com",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate("login_screen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(bottom = 8.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors =  ButtonDefaults.buttonColors(
                containerColor =  MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            )

        ) {
            Text(text = "Editar Perfil")
        }
        Button(
            onClick = {
                navController.navigate("login_screen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(bottom = 8.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors =  ButtonDefaults.buttonColors(
                containerColor =  MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            )

        ) {
            Text(text = "Ajustes")
        }
        Button(
            onClick = {
                navController.navigate("login_screen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(bottom = 16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors =  ButtonDefaults.buttonColors(
                containerColor =  MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            )

        ) {
            Text(text = "Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ProfileTabScreenPreview() {
    FitnessAppTheme {
        ProfileTabScreen(navController = rememberNavController())
    }
}

