package com.example.wisescan.ui.theme.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.ThemeViewModel
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.SETTINGS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController,themeViewModel: ThemeViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "About WISE SCAN", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(SETTINGS)
                }) {
                    androidx.compose.material3.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
        )
        // End of TopAppBar

        // About Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WiseScan",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Version 1.0.0",
                fontSize = 18.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Developed by James Makori",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "WiseScan is an innovative app designed to help you scan, process, and manage documents with ease. Using advanced machine learning, it provides personalized content and automated grading to enhance your productivity.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
