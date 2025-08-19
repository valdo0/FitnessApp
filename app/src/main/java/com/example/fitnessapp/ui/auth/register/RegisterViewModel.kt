package com.example.fitnessapp.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val errorMessage: String? = null,
    val isRegistered:Boolean = false,
)

class RegisterViewModel(
    private val authRepository: AuthRepository = FakeAuthRepository
) : ViewModel() {
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

    fun register() {
        viewModelScope.launch {
            val state = _uiState.value
            if (state.password != state.confirmPassword) {
                _uiState.value = state.copy(errorMessage = "Las contraseñas no coinciden")
                return@launch
            }

            _uiState.value = state.copy(isLoading = true, errorMessage = null)
            val result = authRepository.register(state.email, state.password)

            _uiState.value = result.fold(
                onSuccess = { state.copy(isLoading = false, isRegistered = true) },
                onFailure = { state.copy(isLoading = false, errorMessage = it.message) }
            )
        }
    }
}