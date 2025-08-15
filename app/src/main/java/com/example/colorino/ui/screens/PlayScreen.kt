package com.example.colorino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
fun PlayScreen(navController: NavController,
               modifier: Modifier = Modifier) {
    MainAppLayout(title = "Play Page") {
            paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .background(colorScheme.background)
            .padding(paddingValues)){
            val color = colorScheme.onBackground
            Text("Vamos Jogar!", color = color)
            Text("Primeiro, tire uma foto das suas canetinhas", color = color)
        }
    }
}

@Preview(showBackground = true, name = "Preview Light Theme")
@Composable
fun PlayPreviewLight() {
    ColorinoTheme(darkTheme = false) {
        PlayScreen(navController = rememberNavController(), modifier = Modifier)
    }
}

@Preview(showBackground = true, name = "Preview dark da Theme")
@Composable
fun PlayPreviewDark() {
    ColorinoTheme(darkTheme = true) {
        PlayScreen(navController = rememberNavController(), modifier = Modifier)
    }
}
