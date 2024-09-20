package com.example.wisescan.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    var userName by mutableStateOf("")
    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")

    fun updateUserName(name: String) {
        userName = name
    }


    fun saveProfileChanges() {

    }

    fun updateOldPassword(password: String) {
        oldPassword = password
    }

    fun updateNewPassword(password: String) {
        newPassword = password
    }

    fun changePassword() {

    }

    fun deleteAccount() {

    }

    fun logout() {

    }
}

