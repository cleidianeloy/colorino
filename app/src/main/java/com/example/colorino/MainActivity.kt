package com.example.colorino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.colorino.navigation.AppNavHost
import com.example.colorino.ui.theme.ColorinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorinoTheme{
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}



@Preview(showBackground = true, name = "Preview Light Theme")
@Composable
fun AppPreviewLight() {
    ColorinoTheme(darkTheme = false) {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}

@Preview(showBackground = true, name = "Preview Dark Theme")
@Composable
fun AppPreviewDark() {
    ColorinoTheme(darkTheme = true) {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}