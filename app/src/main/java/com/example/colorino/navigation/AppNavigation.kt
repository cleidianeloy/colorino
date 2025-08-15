package com.example.colorino.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.colorino.ui.screens.MainScreen
import com.example.colorino.ui.screens.MarkersScreen
import com.example.colorino.ui.screens.PlayScreen


object AppDestinations {
    const val MAIN_SCREEN_ROUTE = "main"
    const val MARKERS_ROUTE = "markers"
    const val PLAY_ROUTE = "play"
    // Add other routes as needed
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController,
            startDestination = AppDestinations.MAIN_SCREEN_ROUTE) {
            composable(AppDestinations.MAIN_SCREEN_ROUTE) {
                MainScreen(
                    onNavigateToMarkers = { navController.navigate(AppDestinations.MARKERS_ROUTE) },
                    onNavigateToPlay = { navController.navigate(AppDestinations.PLAY_ROUTE) }
                )
            }
            composable(AppDestinations.MARKERS_ROUTE) {
                MarkersScreen(navController = navController)
            }
            composable(AppDestinations.PLAY_ROUTE) {
                PlayScreen(navController = navController)
            }
            // Add more composable(route) { ... } for other screens
    }
}