package com.example.wisescan

import AppNavHost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.example.wisescan.navigation.LOGIN
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.STORAGE
import com.example.wisescan.ui.theme.WiseScanTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val auth = FirebaseAuth.getInstance()
            val startDestination = if (auth.currentUser != null) DASHBOARD else LOGIN

            WiseScanApp(themeViewModel, startDestination)
        }
    }
}

@Composable
fun WiseScanApp(themeViewModel: ThemeViewModel, startDestination: String) {
    WiseScanTheme(darkTheme = themeViewModel.isDarkTheme.value) {
        AppNavHost(startDestination = startDestination, themeViewModel = themeViewModel)
    }
}

class ThemeViewModel : ViewModel() {
    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String,
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Blue) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Camera,
                            contentDescription = "Capture",
                            tint = Color.White
                        )
                    },
                    label = { Text("Capture", color = Color.White) },
                    selected = false,
                    onClick = {

                    }
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = Color.White
                        )
                    },
                    label = { Text("Home", color = Color.White) },
                    selected = false,
                    onClick = { navController.navigate(DASHBOARD) }
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Storage,
                            contentDescription = "Storage",
                            tint = Color.White
                        )
                    },
                    label = { Text("Storage", color = Color.White) },
                    selected = false,
                    onClick = { navController.navigate(STORAGE) }
                )
            }
        },

    ) {innerPadding ->
        content(innerPadding)
    }
}

