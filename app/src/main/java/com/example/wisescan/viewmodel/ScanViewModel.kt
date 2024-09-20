package com.example.wisescan.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.io.File

open class ScanViewModel : ViewModel() {

    @androidx.camera.core.ExperimentalGetImage
    fun takePhoto(imageCapture: ImageCapture, context: Context, onImageCaptured: (Bitmap?) -> Unit) {
        val tempFile = createTempFile(context)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(tempFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val bitmap = BitmapFactory.decodeFile(tempFile.absolutePath)
                    onImageCaptured(bitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("ScanViewModel", "Photo capture failed: ${exception.message}", exception)
                }
            }
        )
    }

    fun saveImageToFirestore(image: Bitmap?, name: String): Boolean {
        var success = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                image?.let {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    it.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    val imageData = byteArrayOutputStream.toByteArray()

                    val storageRef = FirebaseStorage.getInstance().reference.child("images/$name.jpg")
                    storageRef.putBytes(imageData).await()
                    success = true
                }
            } catch (e: Exception) {
                Log.e("ScanViewModel", "Image upload failed: ${e.message}", e)
                success = false
            }
        }
        return success
    }


    private fun createTempFile(context: Context): File {
        val file = File(context.cacheDir, "temp_image.jpg")
        if (file.exists()) file.delete()
        file.createNewFile()
        return file
    }

    @androidx.camera.core.ExperimentalGetImage
    fun ImageProxy.toBitmap(): Bitmap? {
        val yBuffer = planes[0].buffer // Y
        val uBuffer = planes[1].buffer // U
        val vBuffer = planes[2].buffer // V

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        // U and V are swapped
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
        val imageBytes = out.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}
