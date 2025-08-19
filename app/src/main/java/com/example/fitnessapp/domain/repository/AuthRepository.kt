package com.example.fitnessapp.domain.repository

import com.example.fitnessapp.domain.model.User

interface AuthRepository{
    suspend fun login (email: String, password: String): Result<User>
    suspend fun register (email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getCurrentUser(): User?
}