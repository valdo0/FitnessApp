package com.example.fitnessapp.data.repository

import com.example.fitnessapp.domain.model.User
import com.example.fitnessapp.domain.repository.AuthRepository
import java.util.UUID

object FakeAuthRepository : AuthRepository {

    private val users = mutableMapOf<String, String>()
    private var currentUser: User? = null
    init{
        users["admin@admin.cl"] = "password123"
    }

    override suspend fun login(email: String, password: String): Result<User> {
        val storedPassword = users[email]
        return if (storedPassword != null && storedPassword == password) {
            val user = User(UUID.randomUUID().toString(), email)
            currentUser = user
            Result.success(user)
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }

    override suspend fun register(email: String, password: String): Result<User> {
        if (users.containsKey(email)) {
            return Result.failure(Exception("El usuario ya existe"))
        }
        users[email] = password
        val newUser = User(UUID.randomUUID().toString(), email)
        currentUser = newUser
        return Result.success(newUser)
    }

    override suspend fun logout() {
        currentUser = null
    }
    override suspend fun getCurrentUser(): User? = currentUser

}
