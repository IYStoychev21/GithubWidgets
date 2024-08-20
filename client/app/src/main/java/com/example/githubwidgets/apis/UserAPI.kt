package com.example.githubwidgets.apis

import com.example.githubwidgets.CredentialManager
import com.example.githubwidgets.types.UserType
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.jvm.Throws

object UserAPI {

    @Throws(IOException::class)
    fun getUserProfile(): UserType? {
        if(CredentialManager.getCredential("token") == null)
            return null

        val request = Request.Builder()
            .url("https://github-widgets-api-c9b3bdamdrg0hrg3.germanywestcentral-01.azurewebsites.net/user")
            .addHeader("Authorization", "${CredentialManager.getCredential("token")} ${CredentialManager.getCredential("refresh_token")}")
            .build()

        var response: String?
        ClientInstance.client.newCall(request).execute().use { res ->
            if(!res.isSuccessful) throw IOException("$res.code")
            response = res.body?.string()
        }

        val jsonResponse = JSONObject(response ?: "")

        CredentialManager.storeCredential("token", jsonResponse.getString("token"))
        CredentialManager.storeCredential("refresh_token", jsonResponse.getString("refresh_token"))

        return UserType(
            username = jsonResponse.getString("username"),
            email = jsonResponse.getString("email"),
            name = jsonResponse.getString("name"),
            avatar = jsonResponse.getString("avatar"),
            company = jsonResponse.getString("company")
        )
    }
}