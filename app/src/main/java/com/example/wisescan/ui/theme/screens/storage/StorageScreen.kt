package com.example.wisescan.ui.theme.screens.storage


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.IMPORTANT_DATA
import com.example.wisescan.navigation.RANDOM_DATA
import com.example.wisescan.navigation.SAVED_AUDIOS
import com.example.wisescan.navigation.SAVED_DOCUMENTS
import com.example.wisescan.navigation.SAVED_IMAGES
import com.example.wisescan.navigation.SAVED_VIDEOS
import com.example.wisescan.navigation.SCANNED_DOCUMENTS
import com.example.wisescan.navigation.STORAGE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),

    ) {

        TopAppBar(
            title = { Text(text = "STORAGE", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(DASHBOARD)
                })
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
        )

        Column (
            modifier = Modifier.padding(20.dp)
        ){
           Button(onClick = { navController.navigate(SCANNED_DOCUMENTS)}, modifier = Modifier.fillMaxWidth()) {
           Text(text = "Scanned Documents")
           }
            Divider()
            Button(onClick = { navController.navigate(SAVED_DOCUMENTS) },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Saved Documents")
            }
            Divider()
            Button(onClick = { navController.navigate(SAVED_VIDEOS) },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Saved Videos")
            }
            Button(onClick = { navController.navigate(SAVED_AUDIOS)},modifier = Modifier.fillMaxWidth()) {
                Text(text = "Saved Audios& Podcast")
            }

            Divider()
            Button(onClick = { navController.navigate(SAVED_IMAGES) },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Saved Images")
            }
            Divider()
            Button(onClick = { navController.navigate(IMPORTANT_DATA) },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Important Data")
            }
            Divider()
            Button(onClick = { navController.navigate(RANDOM_DATA) },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Random Data")

            }

        }

    }
}

