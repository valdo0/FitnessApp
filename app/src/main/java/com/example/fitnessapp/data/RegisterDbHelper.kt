package com.example.fitnessapp.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object RegisterDbHelper {
    data class ValidationResut(
        val ok: Boolean,
        val errores: List<String> = emptyList()
    )

    data class LoginResut(
        val ok: Boolean,
        val mensaje: String? = null,
        val usuario: Usuario? = null
    )

    fun validarRegistro(
        context: Context,
        email: String,
        password: String,
        password2: String,
        callback: (ValidationResut) -> Unit
    ) {
        val errores = mutableListOf<String>()
        if (email.isBlank()) {
            errores.add("El email no puede estar vacío")
        }
        if (password.isBlank()) {
            errores.add("La contraseña no puede estar vacía")
        }
        if (password != password2) {
            errores.add("Las contraseñas no coinciden")
        }
        if (errores.isEmpty()) {
            callback(ValidationResut(true))
        } else {
            callback(ValidationResut(false, errores))
        }
        if (errores.isEmpty()) {
            val db = AppDatabase.getDatabase(context)
            CoroutineScope(Dispatchers.IO).launch {
                val usuarioExistente = db.usuarioDao().findByEmail(email)
                if (usuarioExistente != null) {
                    callback(ValidationResut(false, listOf("El usuario ya existe")))
                } else {
                    val usuario = Usuario(email = email, password = password)
                    db.usuarioDao().insert(usuario)
                    callback(ValidationResut(true))
                }
            }
        }else{
            callback(ValidationResut(false, errores))
        }
    }
    suspend fun guardarRegistro(
        context: Context,
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val db = AppDatabase.getDatabase(context)
            val usuarioExistente = db.usuarioDao().findByEmail(email)
            if (usuarioExistente != null) {
                Result.failure(Exception("El usuario ya existe"))
            } else {
                val usuario = Usuario(email = email, password = password)
                db.usuarioDao().insert(usuario)
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    fun login(
        context: Context,
        email: String,
        password: String,
        callback: (LoginResut) -> Unit
    ) {
        val db = AppDatabase.getDatabase(context)

        CoroutineScope(Dispatchers.IO).launch {
            val usuario = db.usuarioDao().findByEmail(email)

            withContext(Dispatchers.Main) {
                if (usuario == null) {
                    callback(LoginResut(false, "Usuario no encontrado"))
                } else if (usuario.password != password) {
                    callback(LoginResut(false, "Contraseña incorrecta"))
                } else {
                    callback(LoginResut(true, "Login exitoso", usuario))
                }
            }
        }
    }

}