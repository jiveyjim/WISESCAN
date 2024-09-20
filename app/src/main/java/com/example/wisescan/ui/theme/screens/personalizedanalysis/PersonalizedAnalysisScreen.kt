package com.example.wisescan.ui.theme.screens.personalizedanalysis

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
fun PersonalizedAnalysisScreen(navController: NavController) {
    AppScaffold(title = "Personalized Analysis", navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(
                title = { Text(text = "PERSONALIZED ANALYSIS", color = Color.White) },
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


            Text(text = "Analysis Results", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))


            Text(text = "Recognized Text:")
            Text(text = "\"Your scanned text here...\"", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Weak Areas Identified:")
            Text(text = "- Topic A: Needs Improvement")
            Text(text = "- Topic B: Requires Review")
            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Suggested Improvements:")
            Text(text = "1. Review topic A materials.")
            Text(text = "2. Practice exercises on topic B.")
        }
    }
}
