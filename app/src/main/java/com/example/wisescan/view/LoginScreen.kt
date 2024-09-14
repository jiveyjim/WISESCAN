package com.example.wisescan.view

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wisescan.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    // Configure Google Sign-In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(stringResource(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    isLoading = true // Start loading
                    firebaseAuthWithGoogle(account, auth, navController, onSignInSuccess = {
                        isLoading = false // Stop loading
                        navController.navigate("scan") {
                            popUpTo("login") { inclusive = true }
                        }
                    }, onSignInError = {
                        isLoading = false // Stop loading on error
                        Log.e("GoogleSignIn", "Google sign in failed: ${it.message}")
                    })
                } else {
                    Log.w("GoogleSignIn", "Google sign in failed: account is null")
                }
            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "Google sign in failed", e)
            }
        } else {
            Log.w("GoogleSignIn", "Google sign in was not successful, resultCode: ${result.resultCode}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator() // Show loading indicator during sign-in
        } else {
            GoogleSignInButton {
                launcher.launch(googleSignInClient.signInIntent)
            }
        }
    }
}

private fun firebaseAuthWithGoogle(
    account: GoogleSignInAccount,
    auth: FirebaseAuth,
    navController: NavHostController,
    onSignInSuccess: () -> Unit,
    onSignInError: (Exception) -> Unit
) {
    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("GoogleSignIn", "signInWithCredential:success")
                onSignInSuccess()
            } else {
                Log.w("GoogleSignIn", "signInWithCredential:failure", task.exception)
                task.exception?.let { onSignInError(it) }
            }
        }
}
