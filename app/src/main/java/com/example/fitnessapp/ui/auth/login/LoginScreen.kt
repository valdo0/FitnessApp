package com.example.fitnessapp.ui.auth.login

import androidx.compose.animation.core.copy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,

        )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth() ,
            colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,

            )
        )
        TextButton(onClick = { navController.navigate("recover_screen") }) {
            Text("¿Olvidaste tu contraseña?")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate("main_screen"){
                    popUpTo("login_screen"){
                        inclusive = true
                    }
                    launchSingleTop=true
                }

                      },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Login")
        }
        TextButton(onClick = { navController.navigate("register_screen") }) {
            Text("¿Aun no tienes una cuenta? Registrate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    FitnessAppTheme {
        LoginScreen(navController = rememberNavController())
    }
}