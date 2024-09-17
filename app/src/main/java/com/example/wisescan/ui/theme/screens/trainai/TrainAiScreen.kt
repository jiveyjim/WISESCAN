package com.example.wisescan.ui.theme.screens.trainai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisescan.AppScaffold
import com.example.wisescan.navigation.DASHBOARD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainAiScreen(navController: NavController) {
    AppScaffold(title = "Train AI", navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            //TopAppBar
            TopAppBar(
                title = { Text(text = "TRAIN & EARN", color = Color.White) },
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

            //End of TopAppbar

            Text(text = "AI Training Instructions", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            // Training Instructions
            Text(text = "1. Print the training documents.")
            Text(text = "2. Fill out the forms with sample data.")
            Text(text = "3. Upload the completed documents.")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Print documents */ }) {
                Text(text = "Print Training Documents")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Upload documents */ }) {
                Text(text = "Upload Training Data")
            }
        }
    }
}
