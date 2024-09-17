package com.example.wisescan.ui.theme.screens.storage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storage
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreen(navController: NavController) {
    var isSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Storage,
            contentDescription = "Storage",
            tint = if (isSelected) Color.Green else Color.White,
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    isSelected = !isSelected
                    // Add your onClick logic here
                }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Storage",
            color = if (isSelected) Color.Green else Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

