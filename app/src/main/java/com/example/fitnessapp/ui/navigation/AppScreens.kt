package com.example.fitnessapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

open class AppScreens (val route:String) {
    object LoginScreen : AppScreens("login_screen")
    object RegisterScreen : AppScreens("register_screen")

    object RecoverPasswordScreen: AppScreens("recover_screen")
    object MainScreen : AppScreens("main_screen")
    object HomeTab : AppScreens("home_tab_screen")
    object ProfileTab : AppScreens("profile_tab_screen")
    object RecipeTab : AppScreens("recipe_tab_screen")
    object FavTab : AppScreens("fav_tab_screen")
}
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Inicio",
        icon = Icons.Outlined.Home,
        route = AppScreens.HomeTab.route
    ),
    BottomNavItem(
        label = "Recetas",
        icon = Icons.Outlined.Create   ,
        route = AppScreens.RecipeTab.route
    ),
    BottomNavItem(
        label = "Favoritos",
        icon = Icons.Outlined.FavoriteBorder,
        route = AppScreens.FavTab.route
    ),
    BottomNavItem(
        label = "Perfil",
        icon = Icons.Outlined.Person,
        route = AppScreens.ProfileTab.route
    ),
)