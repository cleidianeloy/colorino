package com.example.colorino.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.colorino.R

@Composable
fun Logo(modifier: Modifier = Modifier){
    val logoicon = R.drawable.colorino_desenho
    val logoBrand = R.drawable.colorino
    Row(modifier = modifier.fillMaxWidth().padding(start = 45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        Image(modifier = Modifier.size(60.dp), contentScale = ContentScale.Fit, painter = painterResource(logoicon),  contentDescription = "Colorino drawing")
        Image(modifier = Modifier.size(100.dp),contentScale = ContentScale.FillWidth, painter = painterResource(logoBrand),  contentDescription = "Colorino drawing")
    }
}
