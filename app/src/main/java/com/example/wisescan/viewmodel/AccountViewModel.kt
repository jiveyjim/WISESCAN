package com.example.wisescan.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    var userName by mutableStateOf("")
    var userEmail by mutableStateOf("")
    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")

    fun updateUserName(name: String) {
        userName = name
    }

    fun updateUserEmail(email: String) {
        userEmail = email
    }

    fun saveProfileChanges() {
        // Save the profile changes to the server or database
    }

    fun updateOldPassword(password: String) {
        oldPassword = password
    }

    fun updateNewPassword(password: String) {
        newPassword = password
    }

    fun changePassword() {
        // Change the user's password on the server or database
    }

    fun deleteAccount() {
        // Delete the user's account from the server or database
    }

    fun logout() {
        // Perform logout, clear user session
    }
}

