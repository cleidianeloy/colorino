package com.example.colorino.ui.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.colorino.R
import com.example.colorino.navigation.AppDestinations
import com.example.colorino.ui.components.Logo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppLayout(
    title: String = "", // Opcional: título para uma TopAppBar
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Logo(modifier = Modifier.clickable { navController.navigate(AppDestinations.MAIN_SCREEN_ROUTE)})
                },
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.background,
                    titleContentColor = colorScheme.onBackground,
                )
            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                modifier = Modifier.defaultMinSize(minHeight = 100.dp)
            ) {
                val imageResId = R.drawable.canetinhas
                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    Image(painter = painterResource(id = imageResId),
                        contentDescription = null, // Or provide a meaningful description
                        contentScale = ContentScale.FillWidth, // Or other ContentScale options
                        alignment = Alignment.TopStart,
                        modifier = Modifier.fillMaxWidth())
                }
            }
        },
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            content(PaddingValues())
        }
    }
}