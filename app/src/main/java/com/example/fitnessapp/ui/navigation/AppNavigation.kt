package com.example.fitnessapp.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.auth.login.LoginScreen
import com.example.fitnessapp.ui.auth.recoverPassword.RecoverPasswordScreen
import com.example.fitnessapp.ui.auth.register.RegisterScreen
import com.example.fitnessapp.ui.main.MainScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(initialAlpha = 0.1f)
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(targetAlpha = 0.1f)
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(initialAlpha = 0.1f)
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(targetAlpha = 0.1f)
        }

    ) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = AppScreens.RecoverPasswordScreen.route) {
            RecoverPasswordScreen(navController = navController)
        }
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(  mainNavController = navController )
        }

    }
}