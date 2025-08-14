package com.example.colorino.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text

@Composable
fun MarkersScreen(navController: NavController, modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Bem-vindo à Tela Canetinhas!")
    }
}