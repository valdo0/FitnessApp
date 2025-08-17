package com.example.fitnessapp.ui.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.composable
import com.example.fitnessapp.ui.main.tabs.favorite.FavoriteTabScreen
import com.example.fitnessapp.ui.main.tabs.home.HomeTabScreen
import com.example.fitnessapp.ui.main.tabs.profile.ProfileTabScreen
import com.example.fitnessapp.ui.navigation.AppScreens
import com.example.fitnessapp.ui.navigation.bottomNavItems
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier,mainNavController: NavHostController) {
    val bottomBarNavController = rememberNavController()
    var currentTabLabel by rememberSaveable { mutableStateOf(bottomNavItems.first().label) }
    bottomBarNavController.addOnDestinationChangedListener { _, destination, _ ->
        bottomNavItems.find { it.route == destination.route }?.let {
            currentTabLabel = it.label
        }
    }

    Scaffold(
        modifier=modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text =currentTabLabel,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }
            )

        },
        bottomBar = {
            NavigationBar (
                containerColor = MaterialTheme.colorScheme.onSecondary,

            ){
                val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { screen ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label, tint = if (isSelected)  MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.onPrimary) },
                        label = { Text(screen.label,color = if (isSelected)  MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.onPrimary) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            bottomBarNavController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }

                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = Color.Transparent,
                            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
                            disabledTextColor = MaterialTheme.colorScheme.onPrimary,

                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = AppScreens.HomeTab.route,
            modifier = Modifier.padding(innerPadding),
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
            composable(AppScreens.HomeTab.route) { HomeTabScreen() }
            composable(AppScreens.ProfileTab.route) { ProfileTabScreen(navController = mainNavController) }
            composable(AppScreens.FavTab.route){ FavoriteTabScreen() }
            composable(AppScreens.RecipeTab.route){}

        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    FitnessAppTheme {
        MainScreen(
            modifier = Modifier,
            mainNavController = rememberNavController()
        )
    }
}