package com.example.wisescan.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PeerToPeerScreen() {
    val peers = listOf("Peer 1", "Peer 2", "Peer 3")
    val careerAdvice = "Based on your performance, consider pursuing a career in software development."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Peer Matching:")
        peers.forEach { peer ->
            Text(text = peer)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Career Guidance:")
        Text(text = careerAdvice)
    }
}
