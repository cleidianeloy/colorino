package com.example.colorino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Gamepad
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.colorino.navigation.AppDestinations
import com.example.colorino.ui.components.ButtonNav
import com.example.colorino.ui.layouts.MainAppLayout


@Composable
fun MainScreen(navController: NavController) {
    val onNavigateToMarkers = { navController.navigate(AppDestinations.MARKERS_ROUTE) }
    val onNavigateToPlay = { navController.navigate(AppDestinations.PLAY_ROUTE) }
    MainAppLayout(
        title = "Colorino Principal",
        navController = navController) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationButtons(
                onNavigateToMarkers = onNavigateToMarkers,
                onNavigateToPlay = onNavigateToPlay
            )
        }
    }
}

@Composable
fun NavigationButtons(
    onNavigateToMarkers: () -> Unit,
    onNavigateToPlay: () -> Unit){
    FlowRow(verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        val roundedIcons = Icons.Rounded
        ButtonNav(
            texto = "Canetinhas",
            contentColor = colorScheme.primary,
            icon = roundedIcons.Create,
            onClick = onNavigateToMarkers)
        ButtonNav(
            texto = "Jogar",
            contentColor = colorScheme.tertiary,
            icon = roundedIcons.Gamepad,
            onClick = onNavigateToPlay)
    }
}

