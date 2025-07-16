package com.example.colorino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.colorino.ui.theme.ColorinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorinoTheme{

            }
        }
    }
}


@Composable
fun Logo(modifier: Modifier = Modifier){
    val logoicon = R.drawable.colorino_desenho
    val logoBrand = R.drawable.colorino
    Row(modifier, verticalAlignment = Alignment.Top) {
        Image(painter= painterResource(logoicon),  contentDescription = "Colorino drawing")
        Image(painter= painterResource(logoBrand),  contentDescription = "Colorino drawing")
    }
}

@Preview(showBackground = true)
@Composable
fun LogoPreview() {

}