package com.example.wisescan.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wisescan.model.ScanResult
import com.google.firebase.storage.FirebaseStorage
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.ByteArrayOutputStream
import java.util.UUID

open class ScanViewModel : ViewModel() {
    private val storage = FirebaseStorage.getInstance()
    private val _scannedText = mutableStateOf("")
    val scannedText: State<String> = _scannedText

    fun uploadImage(bitmap: Bitmap) {
        val storageRef = storage.reference
        val fileRef = storageRef.child("scanned_documents/${UUID.randomUUID()}.jpg")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        fileRef.putBytes(data)
            .addOnSuccessListener {
                Log.d("ScanViewModel", "Image upload successful")
            }
            .addOnFailureListener {
                Log.e("ScanViewModel", "Image upload failed", it)
            }
    }

    open fun downloadImage(fileName: String, onImageDownloaded: (Bitmap?) -> Unit) {
        val storageRef = storage.reference.child("scanned_documents/$fileName")

        storageRef.getBytes(Long.MAX_VALUE)
            .addOnSuccessListener { bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                onImageDownloaded(bitmap)
            }
            .addOnFailureListener {
                Log.e("ScanViewModel", "Image download failed", it)
            }
    }

    fun performTextRecognition(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                _scannedText.value = visionText.text
                Log.d("ScanViewModel", "Recognized text: ${visionText.text}")
            }
            .addOnFailureListener { e ->
                Log.e("ScanViewModel", "Text recognition failed", e)
            }
    }
}
