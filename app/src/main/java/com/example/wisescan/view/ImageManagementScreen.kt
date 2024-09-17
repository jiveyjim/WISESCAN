package com.example.wisescan.view

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisescan.viewmodel.ScanViewModel

// Mock implementation of ScanViewModel
class MockScanViewModel : ScanViewModel() {
    override fun downloadImage(fileName: String, callback: (Bitmap?) -> Unit) {
        // Generate a placeholder bitmap for the preview
        callback(Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888))
    }
}


@Composable
fun ImageManagementScreen(scanViewModel: ScanViewModel = viewModel()) {
    var downloadedImage by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                val fileName = "your_image_name.jpg" // Change to the actual image name
                scanViewModel.downloadImage(fileName) { bitmap ->
                    downloadedImage = bitmap
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Download Image")
        }

        downloadedImage?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = "Downloaded Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageManagementScreen() {
    // Use the mock view model for preview
    ImageManagementScreen(scanViewModel = MockScanViewModel())
}

