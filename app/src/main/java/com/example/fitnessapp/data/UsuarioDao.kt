package com.example.fitnessapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE email = :email")
    suspend fun findByEmail(email: String): Usuario?

}