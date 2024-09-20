package com.example.wisescan.ui.theme.screens.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.ThemeViewModel
import com.example.wisescan.navigation.ABOUT
import com.example.wisescan.navigation.ACCOUNT_SETTINGS
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.LANGUAGE_SELECT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController, themeViewModel: ThemeViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){


        TopAppBar(
            title = { Text(text = "SETTINGS", color = Color.White) },
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(end = 30.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Dark Mode",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = Color.Blue
            )
            Switch(checked = themeViewModel.isDarkTheme.value, onCheckedChange = {themeViewModel.toggleTheme()})
        }
        Divider()



        TextButton(
            onClick = { navController.navigate(ACCOUNT_SETTINGS)},
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "Account",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        Divider()


        TextButton(
            onClick = { navController.navigate(LANGUAGE_SELECT)},
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "Change Language",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        Divider()


        TextButton(
            onClick = { navController.navigate(ABOUT)},
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "About",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        Divider()
        val context = LocalContext.current

        TextButton(
            onClick = {val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("market://details?id=com.example.yourapp") // Replace with your app's package name
            }
                context.startActivity(intent)},

            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "Rate App",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        Divider()

        val openExitDialog = remember { mutableStateOf(false) }

        if (openExitDialog.value) {
            AlertDialog(
                onDismissRequest = { openExitDialog.value = false },
                title = { Text("Exit") },
                text = { Text("Are you sure you want to exit?") },
                confirmButton = {
                    TextButton(onClick = {
                        openExitDialog.value = false
                        (navController.context as? Activity)?.finish()
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openExitDialog.value = false }) {
                        Text("No")
                    }
                }
            )
        }

        TextButton(
            onClick = {openExitDialog.value = true  },
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Exit",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        Divider()


    }
}




