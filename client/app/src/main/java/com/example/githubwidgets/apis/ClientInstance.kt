package com.example.githubwidgets.apis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ClientInstance {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}