package com.example.fitnessapp.ui.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.RegisterDbHelper
import com.example.fitnessapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.fitnessapp.data.repository.FakeAuthRepository

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false
)

class LoginViewModel(
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun login(context: Context) {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Email y contraseña son requeridos")
            return
        }

        _uiState.value = state.copy(isLoading = true, errorMessage = null)

        RegisterDbHelper.login(context, state.email, state.password) { result ->
            // Este callback se ejecuta en el MainThread
            _uiState.value = if (result.ok) {
                state.copy(isLoading = false, isLoggedIn = true)
            } else {
                state.copy(isLoading = false, errorMessage = result.mensaje)
            }
        }
    }

    fun logout() {
        _uiState.value = LoginUiState()
    }
}