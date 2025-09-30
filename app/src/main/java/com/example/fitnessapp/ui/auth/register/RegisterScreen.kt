package com.example.fitnessapp.ui.auth.register

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Registrarse", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            isError = state.emailError != null,
            supportingText = {
                if (state.emailError != null) {
                    Text(state.emailError!!, color = MaterialTheme.colorScheme.error,fontSize = MaterialTheme.typography.bodyMedium.fontSize)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                errorContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,

                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Contraseña", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = state.passwordError!= null,
            supportingText = {
                if (state.passwordError != null) {
                    Text(state.passwordError!!, color = MaterialTheme.colorScheme.error,fontSize = MaterialTheme.typography.bodyMedium.fontSize)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                errorContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,

                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it)},
            label = { Text("Confirmar Contraseña", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = state.confirmPasswordError!= null,
            supportingText = {
                if (state.confirmPasswordError != null) {
                    Text(state.confirmPasswordError!!, color = MaterialTheme.colorScheme.error,fontSize = MaterialTheme.typography.bodyMedium.fontSize)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                errorContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.register(context)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ){
            Text("Registrar"
                ,fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ,color = MaterialTheme.colorScheme.onSecondary)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("login_screen") }) {
            Text("¿Ya tienes cuenta? Iniciar Sesion"
                , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                )
        }
        val context = LocalContext.current
        LaunchedEffect(state.registrationError) {
            state.registrationError?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            viewModel.clearRegistrationError()
        }
        if(state.isRegistered){
            val context = LocalContext.current
            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
            navController.navigate("login_screen"){
                popUpTo("register_screen"){
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    FitnessAppTheme {
        RegisterScreen(navController = rememberNavController())
    }
}
