package com.example.fitnessapp.ui.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.fitnessapp.ui.main.tabs.favorite.FavoriteTabScreen
import com.example.fitnessapp.ui.main.tabs.home.HomeTabScreen
import com.example.fitnessapp.ui.main.tabs.profile.ProfileTabScreen
import com.example.fitnessapp.ui.main.tabs.recipes.RecipeTabScreen
import com.example.fitnessapp.ui.navigation.AppScreens
import com.example.fitnessapp.ui.navigation.bottomNavItems
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    val drawerNavController = rememberNavController() // Renamed for clarity, was bottomBarNavController
    var currentScreenTitle by rememberSaveable { mutableStateOf(bottomNavItems.first().label) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    drawerNavController.addOnDestinationChangedListener { _, destination, _ ->
        bottomNavItems.find { it.route == destination.route }?.let {
            currentScreenTitle = it.label
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                bottomNavItems.forEach { screen ->
                    val navBackStackEntry by drawerNavController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationDrawerItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = selected,
                        onClick = {
                            scope.launch { drawerState.close() }
                            drawerNavController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(drawerNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = currentScreenTitle,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Abrir menú de navegación",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary // Or your desired color
                    )
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = drawerNavController,
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
                composable(AppScreens.FavTab.route) { FavoriteTabScreen() }
                composable(AppScreens.RecipeTab.route) { RecipeTabScreen() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FitnessAppTheme {
        MainScreen(
            modifier = Modifier,
            mainNavController = rememberNavController()
        )
    }
}
