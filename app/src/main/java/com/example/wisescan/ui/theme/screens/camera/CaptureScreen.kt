package com.example.wisescan.ui.theme.screens.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import androidx.annotation.OptIn
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wisescan.navigation.SCANNED_AI_ANALYSIS
import com.example.wisescan.viewmodel.ScanViewModel
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.coroutines.launch

private const val REQUEST_CAMERA_PERMISSION = 1001

@OptIn(ExperimentalGetImage::class)
@Composable
fun CaptureScreen(scanViewModel: ScanViewModel = viewModel(), navController: NavHostController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var imageCapture by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(Surface.ROTATION_0)
                .build()
        )
    }
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var showEditScreen by remember { mutableStateOf(false) }

    if (showEditScreen) {
        EditScreen(
            image = capturedImage,
            onSave = { name ->
                coroutineScope.launch {
                    val success = scanViewModel.saveImageToFirestore(capturedImage, name)
                    if (success) {
                        snackbarHostState.showSnackbar("Image saved successfully")
                    } else {
                        snackbarHostState.showSnackbar("Failed to save image")
                    }
                    showEditScreen = false
                }
            },
            onCancel = {
                showEditScreen = false
            },
            navController = navController
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val lifecycleOwner = LocalContext.current as LifecycleOwner
            val previewView = remember { PreviewView(context) }
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
                } catch (exc: Exception) {
                    Log.e("CameraPreview", "Use case binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(context))

            AndroidView(
                factory = { previewView },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            capturedImage?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            Button(
                onClick = {
                    checkPermissionsAndStartCamera(context as LifecycleOwner) {
                        scanViewModel.takePhoto(imageCapture, context) { bitmap ->
                            bitmap?.let { img ->
                                capturedImage = img
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Capture")
            }

            if (capturedImage != null) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    val success = scanViewModel.saveImageToFirestore(capturedImage, "Image Name")
                                    if (success) {
                                        snackbarHostState.showSnackbar("Image saved successfully")
                                    } else {
                                        snackbarHostState.showSnackbar("Failed to save image")
                                    }
                                }
                            }
                        ) {
                            Text("Save")
                        }
                        Button(
                            onClick = {
                                showEditScreen = true
                            }
                        ) {
                            Text("Edit")
                        }
                        Button(
                            onClick = {
                                navController.navigate("dashboard")
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { navController.navigate(SCANNED_AI_ANALYSIS) }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "AI Analysis")
                    }
                }
            }

            SnackbarHost(hostState = snackbarHostState)
        }
    }
}

private fun checkPermissionsAndStartCamera(
    lifecycleOwner: LifecycleOwner,
    onPermissionGranted: () -> Unit
) {
    val context = (lifecycleOwner as? Activity)?.applicationContext
    if (context != null && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        onPermissionGranted()
    } else if (lifecycleOwner is Activity) {
        ActivityCompat.requestPermissions(
            lifecycleOwner,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CAMERA_PERMISSION
        )
    }
}

@Composable
fun EditScreen(
    image: Bitmap?,
    onSave: (String) -> Unit,
    onCancel: () -> Unit,
    navController: NavHostController
) {
    var documentName by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        image?.let {
            AndroidView(
                factory = { ctx ->
                    PhotoView(ctx).apply {
                        setImageBitmap(it)
                        isZoomable = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 16.dp)
            )
        }

        TextField(
            value = documentName,
            onValueChange = { documentName = it },
            label = { Text("Document Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (documentName.isNotBlank()) {
                        onSave(documentName)
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter a document name")
                        }
                    }
                }
            ) {
                Text("Save")
            }
            Button(
                onClick = onCancel
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    navController.navigate(SCANNED_AI_ANALYSIS)
                }
            ) {
                Text("AI Analysis")
            }
        }
    }
}
