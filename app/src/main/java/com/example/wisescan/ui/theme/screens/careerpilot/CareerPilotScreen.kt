package com.example.wisescan.ui.theme.screens.careerpilot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun CareerPilotScreen(navController: NavController) {
    AppScaffold(title = "Career Pilot",navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(
                title = { Text(text = "CAREER PILOT", color = Color.White) },
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

            Text(text = "Your Career Pathway", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Based on Your Strengths:")
            Text(text = "1. Data Scientist")
            Text(text = "2. Software Engineer")
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Resources:")
            Text(text = "- Guide: How to Become a Data Scientist.pdf")
            Text(text = "- Course: Introduction to Programming")
        }
    }
}
