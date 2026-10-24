package com.example.colorino.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.colorino.ui.screens.CameraHandle
import com.example.colorino.ui.screens.MainScreen
import com.example.colorino.ui.screens.MarkersScreen
import com.example.colorino.ui.screens.PlayScreen
import com.example.colorino.ui.screens.CameraScreen


object AppDestinations {
    const val MAIN_SCREEN_ROUTE = "main"
    const val MARKERS_ROUTE = "markers"
    const val PLAY_ROUTE = "play"
    const val CAMERA_ROUTE = "camera"
    const val CAMERA_HANDLE_BASE = "camera_handle"
    const val CAMERA_HANDLE_ROUTE = "$CAMERA_HANDLE_BASE/{imageUri}"
    const val IMAGE_ARG = "imageUri"
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController,
            startDestination = AppDestinations.MAIN_SCREEN_ROUTE) {
            composable(AppDestinations.MAIN_SCREEN_ROUTE) {
                MainScreen(navController = navController)
            }
            composable(AppDestinations.MARKERS_ROUTE) {
                MarkersScreen(navController = navController)
            }
            composable(AppDestinations.PLAY_ROUTE) {
                PlayScreen(navController = navController)
            }
            composable(AppDestinations.CAMERA_ROUTE) {
                CameraScreen(navController = navController,
                    onImageCaptured = { uri ->
                        val encodedUri = java.net.URLEncoder.encode(uri.toString(), "UTF-8")

                        navController.navigate("${AppDestinations.CAMERA_HANDLE_BASE}/$encodedUri") {
                            popUpTo(AppDestinations.CAMERA_HANDLE_ROUTE) { inclusive = true }
                        }
                    })
            }
        composable(AppDestinations.CAMERA_HANDLE_ROUTE){ backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString(AppDestinations.IMAGE_ARG)

            CameraHandle(
                navController = navController,
                imageUri = imageUri // Você precisará adicionar esse parâmetro no CameraHandle
            )
        }
            // Add more composable(route) { ... } for other screens
    }
}