package com.example.wisescan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wisescan.view.CaptureScreen
import com.example.wisescan.view.ImageManagementScreen
import com.example.wisescan.view.LoginScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        setContent {
            WiseScanApp()
        }
    }
}

@Composable
fun WiseScanApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Camera, contentDescription = "Capture") },
                    label = { Text("Capture") },
                    selected = false,
                    onClick = { navController.navigate("capture") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Image, contentDescription = "Manage") },
                    label = { Text("Manage") },
                    selected = false,
                    onClick = { navController.navigate("imageManagement") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController) }
            composable("capture") { CaptureScreen() }
            composable("imageManagement") { ImageManagementScreen() }
        }
    }
}
