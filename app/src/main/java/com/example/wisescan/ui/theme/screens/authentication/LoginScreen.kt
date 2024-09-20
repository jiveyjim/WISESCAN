package com.example.wisescan.ui.theme.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.FORGOT_PASSWORD
import com.example.wisescan.navigation.LOGIN
import com.example.wisescan.navigation.SIGNUP
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WISE SCAN",
            fontSize = 60.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Scan,Learn,Succeed",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate(DASHBOARD) {
                            popUpTo(LOGIN) { inclusive = true }
                        }
                    } else {
                        errorMessage = task.exception?.message
                    }
                }
        },modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { navController.navigate(SIGNUP) }) {
            Text("Sign Up")
        }
        errorMessage?.let { Text(text = it, color = MaterialTheme.colors.error) }
        Spacer(modifier = Modifier.height(30.dp))
        
        TextButton(onClick = {  navController.navigate(FORGOT_PASSWORD)}) {
            Text(text = "Forgot Password?")
        }

    }
}

