package com.example.githubwidgets.Storage

import android.content.Context
import android.content.SharedPreferences

object UserStorage {
    fun initPref(context: Context) {
        m_UserPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun storeValue(key: String, value: String) {
        with(m_UserPrefs.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getValue(key: String): String? {
        return m_UserPrefs.getString(key, null)
    }

    private lateinit var m_UserPrefs: SharedPreferences;
}