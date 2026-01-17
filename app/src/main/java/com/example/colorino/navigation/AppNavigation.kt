package com.example.colorino.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.colorino.ui.screens.MainScreen
import com.example.colorino.ui.screens.MarkersScreen
import com.example.colorino.ui.screens.PlayScreen
import com.example.colorino.ui.screens.CameraScreen


object AppDestinations {
    const val MAIN_SCREEN_ROUTE = "main"
    const val MARKERS_ROUTE = "markers"
    const val PLAY_ROUTE = "play"
    const val CAMERA_ROUTE = "camera"
    const val CAPTURED_IMAGE_URI_KEY = "captured_image_uri"
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
                        // 1. Pegue a "pilha de volta" da navegação.
                        //    A entrada anterior (previousBackStackEntry) é a tela que te chamou (ex: PlayScreen).
                        val previousScreenState = navController.previousBackStackEntry?.savedStateHandle

                        // 2. Salve o URI da imagem nesse estado.
                        //    A tela anterior poderá ler este valor.
                        //    Usamos AppDestinations.CAPTURED_IMAGE_URI_KEY como chave para evitar erros de digitação.
                        previousScreenState?.set(AppDestinations.CAPTURED_IMAGE_URI_KEY, uri.toString())

                        // 3. Volte para a tela anterior.
                        //    A CameraScreen já faz isso internamente após a captura,
                        //    mas se não fizesse, você chamaria aqui:
                        // navController.popBackStack()
                    })
            }
            // Add more composable(route) { ... } for other screens
    }
}