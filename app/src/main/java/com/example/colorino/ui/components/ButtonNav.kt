package com.example.colorino.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonNav(
    texto: String,
    contentColor: Color,
    icon: ImageVector,
    width: Int = 130,
    onClick: () -> Unit,
){
    val buttonModifier = Modifier
        .width(width.dp)
        .aspectRatio(1f / 1f)
        .border(3.dp, contentColor, RoundedCornerShape(15.dp))
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor =  colorScheme.surface,
        disabledContentColor = colorScheme.onSurface)
    Button(onClick = onClick,
        colors = buttonColors,
        modifier = buttonModifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Icon(imageVector = icon,
                contentDescription = texto,
                modifier = Modifier.size(40.dp))
            Text(texto)
        }
    }
}