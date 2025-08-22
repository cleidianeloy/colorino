package com.example.colorino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.colorino.navigation.AppDestinations
import com.example.colorino.ui.components.ButtonNav
import com.example.colorino.ui.layouts.MainAppLayout
import com.example.colorino.ui.theme.ColorinoTheme

@Composable
fun PlayScreen(navController: NavController,
               modifier: Modifier = Modifier) {
    MainAppLayout(title = "Play Page",
        navController = navController) {
            paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .background(colorScheme.background)
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            val color = colorScheme.tertiary
            Text("Vamos Jogar!",
                color = color,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold )
            Text("Agora é o momento de escolher as canetinhas que você vai usar",
                color = color,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp))
            FlowRow(modifier = Modifier.padding(top = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),) {
                val roundedIcons = Icons.Rounded
                ButtonNav(
                    texto = "Canetinhas",
                    contentColor = colorScheme.primary,
                    icon = roundedIcons.Create,
                    onClick = {navController.navigate(AppDestinations.MARKERS_ROUTE)})
                ButtonNav(
                    texto = "Nova",
                    contentColor = colorScheme.secondary,
                    icon = roundedIcons.Upload,
                    onClick = {navController.navigate(AppDestinations.PLAY_ROUTE)})
            }
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
