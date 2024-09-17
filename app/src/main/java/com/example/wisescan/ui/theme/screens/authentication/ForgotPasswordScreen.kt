package com.example.wisescan.ui.theme.screens.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.navigation.LOGIN
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var emailSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(27.dp)
    ) {
        Text(text = "Forgot Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Call Firebase function to send password reset email
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emailSent = true
                    } else {
                        // Handle error
                    }
                }
        },modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Send Reset Code")
        }
        if (emailSent) {
            Text("Reset link sent to $email. Please check your email and come back to log in with your new password")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {  navController.navigate(LOGIN) },modifier = Modifier.align(Alignment.CenterHorizontally) ){
            Text(text = "Login")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ForgotPasswordScreenPreview(){
    ForgotScreenDemo()
}

@Composable
fun ForgotScreenDemo(){
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(27.dp)
    ) {
        Text(text = "Forgot Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ },modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Send Reset Code")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { },modifier = Modifier.align(Alignment.CenterHorizontally) ){
            Text(text = "Login")
        }
    }
}
