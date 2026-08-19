package com.example.colorino.ui.screens
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val TAG = "CameraScreen"
private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

@Composable
fun CameraScreen(
    navController: NavController,
    onImageCaptured: (Uri) -> Unit // Callback para retornar o URI da imagem capturada
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var previewView: PreviewView? by remember { mutableStateOf(null) }



    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    layoutParams =
                        android.view.ViewGroup.LayoutParams(
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    // Implemente o Pinch to Zoom (opcional)
                    // implementPinchToZoom(this)
                }.also {
                    previewView = it
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = {
                imageCapture?.let { capture ->
                    takePhoto(
                        imageCapture = capture,
                        context = context,
                        executor = ContextCompat.getMainExecutor(context), // Ou um executor em background
                        onImageCaptured = { uri ->
                            Log.d(TAG, "Imagem capturada: $uri")
                            onImageCaptured(uri) // Retorna o URI
                            /*TODO criar uma tela ou trabalhar nas que existem para que aceitem a nova foto*/
                            navController.popBackStack() // Volta para a tela anterior
                        },
                        onError = { exception ->
                            Log.e(TAG, "Erro ao capturar imagem:", exception)
                            // Tratar erro (ex: mostrar Toast)
                        }
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(32.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape // Pinta o fundo de circular
        )
        ) {
            Icon(
                imageVector = Icons.Sharp.Lens,
                contentDescription = "Tirar Foto",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
        IconButton(onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.TopEnd).statusBarsPadding() ) {
            Icon(
                imageVector = Icons.Sharp.Close,
                contentDescription = "Voltar",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp))
        }
    }

    LaunchedEffect(cameraProviderFuture) {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView?.surfaceProvider)
        }
        imageCapture = ImageCapture.Builder().build()

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            // Desvincular casos de uso antes de revincular
            cameraProvider.unbindAll()
            // Vincular casos de uso à câmera
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        } catch (exc: Exception) {
            Log.e(TAG, "Falha ao vincular casos de uso da câmera", exc)
        }
    }
}


// Função para tirar a foto (pode ser movida para um arquivo utilitário de câmera)
private fun takePhoto(
    imageCapture: ImageCapture,
    context: Context,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val photoFile = File(
        context.filesDir, // Salva no diretório interno específico do app (mais privado)
        // Ou use MediaStore para salvar em armazenamento compartilhado visível por outras galerias
        SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
                onImageCaptured(savedUri)
            }

            override fun onError(exc: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                onError(exc)
            }
        }
    )
}