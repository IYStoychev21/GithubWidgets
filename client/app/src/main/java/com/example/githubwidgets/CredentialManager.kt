package com.example.githubwidgets
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object CredentialManager {
    fun initCredentialPrefs(context: Context) {
        m_CredentialPrefs = EncryptedSharedPreferences.create(
            context,
            "credentials_prefs",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun storeCredential(key: String, value: String) {
        with(m_CredentialPrefs.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getCredential(key: String): String? {
        return m_CredentialPrefs.getString(key, null)
    }

    fun clearCredential(key: String) {
        with(m_CredentialPrefs.edit()) {
            remove(key)
            apply()
        }
    }

    private lateinit var m_CredentialPrefs: SharedPreferences;
}