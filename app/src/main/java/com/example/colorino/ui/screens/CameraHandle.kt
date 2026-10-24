package com.example.colorino.ui.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.toInt
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.colorino.ui.layouts.MainAppLayout


@Composable
fun CameraHandle(navController: NavController, imageUri: String?) {
    val context = LocalContext.current
    val uri = remember(imageUri) { imageUri?.let { Uri.parse(it) } }

    // Estados para o Pin e a Cor
    var pinPosition by remember { mutableStateOf<Offset?>(null) }
    var selectedColor by remember { mutableStateOf(Color.Transparent) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    // Carrega o Bitmap da imagem para permitir a extração de pixels
    val bitmap = remember(uri) {
        uri?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                    decoder.isMutableRequired = true // Permite manipulação se necessário
                }
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
        }
    }

    MainAppLayout(
        title = "Selecionar Cor",
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Container da Imagem com detecção de toque
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .onGloballyPositioned { containerSize = it.size }
                    .pointerInput(bitmap) {
                        detectTapGestures { offset ->
                            bitmap?.let { btm ->
                                pinPosition = offset
                                // Lógica de extração de cor
                                selectedColor = extractColorFromBitmap(btm, offset, containerSize)
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (uri != null) {
                    AsyncImage(
                        model = uri,
                        contentDescription = "Foto capturada",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    pinPosition?.let { position ->
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .offset {
                                    IntOffset(
                                        x = (position.x - with(density) { 16.dp.toPx() }).toInt(),
                                        y = (position.y - with(density) { 32.dp.toPx() }).toInt()
                                    )
                                }
                                .rotate(-45f)
                        ) {
                            // ÍCONE DE FUNDO (Borda Branca)
                            Icon(
                                imageVector = Icons.Rounded.Create,
                                contentDescription = null,
                                tint = Color.White, // Borda branca
                                modifier = Modifier
                                    .size(36.dp) // Um pouco maior que o ícone da frente
                                    .offset(x = (-1).dp, y = (-1).dp) // Ajuste fino para centralizar a borda
                            )

                            // ÍCONE DA FRENTE (Cor Selecionada)
                            Icon(
                                imageVector = Icons.Rounded.Create,
                                contentDescription = null,
                                tint = if (selectedColor == Color.Transparent) colorScheme.primary else selectedColor,
                                modifier = Modifier.size(32.dp)
                            )

                            // Pequeno ponto de brilho na ponta para dar precisão (opcional)
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(Color.White, shape = CircleShape)
                                    .align(Alignment.BottomStart) // Alinha com a ponta do ícone Create
                            )
                        }
                    }
                }
            }

            // Preview da Cor Selecionada
            Card(
                modifier = Modifier.padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = selectedColor.copy(alpha = 1f))
            ) {
                //TODO: fazer uma lista de cores.
                Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
                    if (selectedColor == Color.Transparent) {
                        Text("?", color = colorScheme.onSurface)
                    }
                }
            }

            // Botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) { Text("Voltar") }

                Button(
                    onClick = {
                        //TODO: salvar o estojo de canetinhas "canetinha" - MarkersScreen
                    },
                    enabled = selectedColor != Color.Transparent,
                    modifier = Modifier.weight(1f)
                ) { Text("Salvar") }
            }
        }
    }
}

// Função auxiliar para mapear o toque e pegar o pixel
fun extractColorFromBitmap(bitmap: Bitmap, tapOffset: Offset, containerSize: IntSize): Color {
    // 1. Calcular a escala da imagem dentro do container (ContentScale.Fit)
    val scaleX = containerSize.width.toFloat() / bitmap.width
    val scaleY = containerSize.height.toFloat() / bitmap.height
    val scale = minOf(scaleX, scaleY)

    // 2. Calcular as bordas (o espaço vazio quando a imagem não preenche tudo)
    val offsetX = (containerSize.width - (bitmap.width * scale)) / 2f
    val offsetY = (containerSize.height - (bitmap.height * scale)) / 2f

    // 3. Mapear a coordenada do toque para a coordenada do pixel no Bitmap
    val bitmapX = ((tapOffset.x - offsetX) / scale).toInt().coerceIn(0, bitmap.width - 1)
    val bitmapY = ((tapOffset.y - offsetY) / scale).toInt().coerceIn(0, bitmap.height - 1)

    // 4. Pegar a cor do pixel
    val pixel = bitmap.getPixel(bitmapX, bitmapY)
    return Color(pixel)
}