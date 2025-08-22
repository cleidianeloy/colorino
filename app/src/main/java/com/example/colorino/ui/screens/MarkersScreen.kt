package com.example.colorino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.colorino.ui.layouts.MainAppLayout
import com.example.colorino.ui.theme.ColorinoTheme

@Composable
fun MarkersScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MainAppLayout(
        title = "Markers Page",
        navController = navController) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(paddingValues)
        ) {
            val color = colorScheme.onBackground
            Text("Bem-vindo à Tela Canetinhas!", color = color)
        }
    }
}

@Preview(showBackground = true, name = "Preview Light Theme")
@Composable
fun MarkersPreviewLight() {
    ColorinoTheme(darkTheme = false) {
        MarkersScreen(navController = rememberNavController(), modifier = Modifier)
    }
}

@Preview(showBackground = true, name = "Preview dark da Theme")
@Composable
fun MarkersPreviewDark() {
    ColorinoTheme(darkTheme = true) {
        MarkersScreen(navController = rememberNavController(), modifier = Modifier)
    }
}