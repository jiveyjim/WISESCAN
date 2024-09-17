package com.example.wisescan.ui.theme.screens.personalizedcontent

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
fun PersonalizedContentScreen(navController: NavController) {
    AppScaffold(title = "Personalized Content",navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            //TopAppBar
            TopAppBar(
                title = { Text(text = "PERSONALIZED CONTENT", color = Color.White) },
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

            Text(text = "Your Personalized Study Materials", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            // Recommended PDFs
            Text(text = "PDF Notes:")
            Text(text = "- Introduction to Calculus.pdf")
            Spacer(modifier = Modifier.height(8.dp))

            // Recommended Videos
            Text(text = "Videos:")
            Text(text = "- Video: Understanding Derivatives.mp4")
            Spacer(modifier = Modifier.height(8.dp))

            // Recommended Quizzes
            Text(text = "Quizzes:")
            Text(text = "- Quiz: Basic Algebra Concepts")
        }
    }
}
