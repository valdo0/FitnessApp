package com.example.fitnessapp.ui.main.tabs.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeTabScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Text("Home Screen")
    }
}