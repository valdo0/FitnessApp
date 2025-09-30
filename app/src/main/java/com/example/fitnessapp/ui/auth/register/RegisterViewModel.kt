package com.example.fitnessapp.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import com.example.fitnessapp.data.RegisterDbHelper
import com.example.fitnessapp.data.RegisterDbHelper.guardarRegistro
import com.example.fitnessapp.data.repository.FakeAuthRepository
import com.example.fitnessapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val registrationError: String? = null,
    val isRegistered:Boolean = false,
)

class RegisterViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }

    fun validateInput(): Boolean {
        val state = _uiState.value
        val emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            "Email no válido"
        } else null

        val passwordError = if (state.password.length < 8) {
            "Contraseña debe tener al menos 8 caracteres"
        } else null

        val confirmPasswordError = if (state.password != state.confirmPassword) {
            "Las contraseñas no coinciden"
        } else null

        _uiState.value = state.copy(
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )

        return emailError == null && passwordError == null && confirmPasswordError == null
    }

    fun clearRegistrationError() {
        _uiState.value = _uiState.value.copy(registrationError = null)
    }

    fun register(context: Context) {
        if (!validateInput()) return

        viewModelScope.launch {
            val state = _uiState.value
            _uiState.value = state.copy(isLoading = true, registrationError = null)

            val result = RegisterDbHelper.guardarRegistro(context, state.email, state.password)

            _uiState.value = result.fold(
                onSuccess = { state.copy(isLoading = false, isRegistered = true) },
                onFailure = { state.copy(isLoading = false, registrationError = it.message) }
            )
        }
    }
}
