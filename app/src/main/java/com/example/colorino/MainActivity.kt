package com.example.colorino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Gamepad
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.colorino.ui.theme.ColorinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorinoTheme{
                Main()
            }
        }
    }
}


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
@Composable
fun Navigation(){
    FlowRow(verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        val roundedIcons = Icons.Rounded;
        ButtonNav(
            texto = "Canetinhas",
            contentColor = MaterialTheme.colorScheme.primary,
            icon = roundedIcons.Create)
        ButtonNav(
            texto = "Jogar",
            contentColor = MaterialTheme.colorScheme.secondary,
            icon = roundedIcons.Gamepad)
    }
}
@Composable
fun ButtonNav(texto: String,
              contentColor: Color,
              icon: ImageVector
){
    val buttonModifier = Modifier.width(130.dp)
        .aspectRatio(1f/1f)
        .border(3.dp, contentColor, RoundedCornerShape(15.dp) )
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = contentColor,
        disabledContainerColor =  MaterialTheme.colorScheme.surface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface)
    Button(onClick = {},
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
@Composable
fun Main(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Logo()
        Navigation()
    }
}

@Preview(showBackground = true, name = "Preview Tema Claro")
@Composable
fun AppPreviewLight() {
    ColorinoTheme(darkTheme = false) {
        Main()
    }
}

@Preview(showBackground = true, name = "Preview Tema Escuro")
@Composable
fun AppPreviewDark() {
    ColorinoTheme(darkTheme = true) {
        Main()
    }
}