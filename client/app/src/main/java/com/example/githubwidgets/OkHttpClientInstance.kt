package com.example.githubwidgets

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import kotlin.jvm.Throws

object OkHttpClientInstance {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Throws(IOException::class)
    fun getUserProfile(): String? {
        if(CredentialManager.getToken() == null)
            return ""

        val request = Request.Builder()
            .url("https://github-widgets-api-c9b3bdamdrg0hrg3.germanywestcentral-01.azurewebsites.net/user")
            .addHeader("Authorization", "${CredentialManager.getToken()} ${CredentialManager.getRefreshToken()}")
            .build()

        client.newCall(request).execute().use { response ->
            if(!response.isSuccessful) throw IOException("$response.code")
            return response.body?.string()
        }
    }
}