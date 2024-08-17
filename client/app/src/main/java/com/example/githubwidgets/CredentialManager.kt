package com.example.githubwidgets

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object CredentialManager {
    lateinit var sharedPreferences: SharedPreferences

    fun storeToken(token: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun storeUsername(username: String) {
        with(sharedPreferences.edit()) {
            putString("username", username)
            apply()
        }
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun clearToken() {
        with(sharedPreferences.edit()) {
            remove("token")
            apply()
        }
    }
}