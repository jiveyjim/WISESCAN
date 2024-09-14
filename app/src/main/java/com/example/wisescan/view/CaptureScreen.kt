package com.example.wisescan.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.YuvImage
import android.media.Image
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisescan.viewmodel.ScanViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

@Composable
fun CaptureScreen(scanViewModel: ScanViewModel = viewModel()) {
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CameraPreview(modifier = Modifier.weight(1f), onImageCaptured = { capture ->
            imageCapture = capture
        })

        Button(
            onClick = {
                imageCapture?.let {
                    takePhoto(it, context) { bitmap ->
                        bitmap?.let { img ->
                            performTextRecognition(img)
                            uploadImageToFirebase(img, context)
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Capture and Scan")
        }
    }
}

private fun takePhoto(imageCapture: ImageCapture, context: Context, onImageCaptured: (Bitmap?) -> Unit) {
    val outputOptions = ImageCapture.OutputFileOptions.Builder(createTempFile(context)).build()
    imageCapture.takePicture(ContextCompat.getMainExecutor(context), object : ImageCapture.OnImageCapturedCallback() {
        @OptIn(ExperimentalGetImage::class)
        override fun onCaptureSuccess(imageProxy: ImageProxy) {
            val bitmap = imageProxy.image?.toBitmap()
            onImageCaptured(bitmap)
            imageProxy.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CaptureScreen", "Photo capture failed: ${exception.message}", exception)
        }
    })
}

private fun Image?.toBitmap(): Bitmap? {
    this ?: return null

    // Ensure the image format is YUV_420_888
    if (this.format != ImageFormat.YUV_420_888) {
        throw IllegalArgumentException("Unsupported image format: ${this.format}")
    }

    val yPlane = this.planes[0]
    val uPlane = this.planes[1]
    val vPlane = this.planes[2]

    val yBuffer = yPlane.buffer
    val uBuffer = uPlane.buffer
    val vBuffer = vPlane.buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val yData = ByteArray(ySize)
    val uData = ByteArray(uSize)
    val vData = ByteArray(vSize)

    yBuffer.get(yData)
    uBuffer.get(uData)
    vBuffer.get(vData)

    val nv21Data = ByteArray(yData.size + uData.size + vData.size)
    System.arraycopy(yData, 0, nv21Data, 0, yData.size)
    System.arraycopy(vData, 0, nv21Data, yData.size, vData.size)
    System.arraycopy(uData, 0, nv21Data, yData.size + vData.size, uData.size)

    val nv21Image = YuvImage(nv21Data, ImageFormat.NV21, width, height, null)
    val outputStream = ByteArrayOutputStream()
    nv21Image.compressToJpeg(android.graphics.Rect(0, 0, width, height), 100, outputStream)
    val jpegData = outputStream.toByteArray()

    return BitmapFactory.decodeByteArray(jpegData, 0, jpegData.size)
}

private fun createTempFile(context: Context): File {
    val file = File(context.cacheDir, "temp_image.jpg")
    if (file.exists()) file.delete()
    file.createNewFile()
    return file
}

private fun uploadImageToFirebase(bitmap: Bitmap, context: Context) {
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val fileRef = storageRef.child("scanned_documents/${UUID.randomUUID()}.jpg")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    val uploadTask = fileRef.putBytes(data)

    uploadTask.addOnSuccessListener {
        Log.d("CaptureScreen", "Image upload successful")
    }.addOnFailureListener {
        Log.e("CaptureScreen", "Image upload failed", it)
    }
}

private fun performTextRecognition(bitmap: Bitmap) {
    val image = InputImage.fromBitmap(bitmap, 0)
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    recognizer.process(image)
        .addOnSuccessListener { visionText ->
            Log.d("CaptureScreen", "Recognized text: ${visionText.text}")
        }
        .addOnFailureListener { e ->
            Log.e("CaptureScreen", "Text recognition failed", e)
        }
}
