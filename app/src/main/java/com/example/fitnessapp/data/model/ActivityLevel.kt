package com.example.fitnessapp.data.model

enum class ActivityLevel(val display: String, val factor: Double) {
    Sedentario("Sedentario (poco o nada de ejercicio)", 1.2),
    Ligero("Ligero (1-3 días/semana)", 1.375),
    Moderado("Moderado (3-5 días/semana)", 1.55),
    Intenso("Intenso (6-7 días/semana)", 1.725),
    MuyIntenso("Muy intenso (2 veces al día)", 1.9)
}
