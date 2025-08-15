package com.example.fitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.auth.login.LoginScreen
import com.example.fitnessapp.ui.auth.register.RegisterScreen
import com.example.fitnessapp.ui.main.MainScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.route,

    ) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(  mainNavController = navController )
        }

    }
}