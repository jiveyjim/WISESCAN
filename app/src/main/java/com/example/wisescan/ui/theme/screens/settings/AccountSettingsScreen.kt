package com.example.wisescan.ui.theme.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wisescan.ThemeViewModel
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.viewmodel.AccountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    navController: NavHostController,
    viewModel: AccountViewModel // ViewModel to handle account-related operations
) {
    val showChangePasswordDialog = remember { mutableStateOf(false) }
    val showDeleteAccountDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "Account Settings", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(DASHBOARD) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
        )

        // Edit Profile Section
        Text(text = "Edit Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // User's name can be changed
        TextField(
            value = viewModel.userName,
            onValueChange = { viewModel.updateUserName(it) },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

       

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Change Password Section
        TextButton(onClick = { showChangePasswordDialog.value = true }) {
            Text("Change Password")
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Other sections like Delete Account and Logout
        TextButton(onClick = { showDeleteAccountDialog.value = true }) {
            Text("Delete Account")
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { viewModel.saveProfileChanges() }) {
            Text("Save Changes")
        }
        Spacer(modifier = Modifier.height(35.dp))

        TextButton(onClick = {
            viewModel.logout()
            navController.navigate("login") // Navigate to login screen
        }) {
            Text("Log Out")
        }

        // Change Password Dialog
        if (showChangePasswordDialog.value) {
            AlertDialog(
                onDismissRequest = { showChangePasswordDialog.value = false },
                title = { Text("Change Password") },
                text = {
                    Column {
                        TextField(
                            value = viewModel.oldPassword,
                            onValueChange = { viewModel.updateOldPassword(it) },
                            label = { Text("Old Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = viewModel.newPassword,
                            onValueChange = { viewModel.updateNewPassword(it) },
                            label = { Text("New Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.changePassword()
                        showChangePasswordDialog.value = false
                    }) {
                        Text("Change")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showChangePasswordDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Delete Account Dialog
        if (showDeleteAccountDialog.value) {
            AlertDialog(
                onDismissRequest = { showDeleteAccountDialog.value = false },
                title = { Text("Delete Account") },
                text = { Text("Are you sure you want to delete your account? This action cannot be undone.") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteAccount()
                        navController.navigate("login") // Navigate to login screen
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteAccountDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
