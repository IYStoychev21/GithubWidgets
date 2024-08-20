package com.example.githubwidgets.apis

import com.example.githubwidgets.Storage.UserStorage
import com.example.githubwidgets.types.ContributionType
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.jvm.Throws

object ContributionsAPI {
    @Throws(IOException::class)
    fun getContributions(): List<ContributionType> {

        val request = Request.Builder()
            .url("https://github-widgets-api-c9b3bdamdrg0hrg3.germanywestcentral-01.azurewebsites.net/get/contributions/${UserStorage.getValue("username")}")
            .build()

        val response: String?

        ClientInstance.client.newCall(request).execute().use { res ->
            if(!res.isSuccessful) throw IOException("$res.code")
            response = res.body?.string()
        }

        val jsonResponse = JSONObject(response ?: "")

        val contributions = mutableListOf<ContributionType>()

        for (i in 0..jsonResponse.getJSONArray("contributions").length() - 1) {
            val element = jsonResponse.getJSONArray("contributions").get(i) as JSONObject
            val contribution = ContributionType(
                date = element.getString("date"),
                count = element.getInt("count"),
                level = element.getInt("level")
            )

            contributions.add(contribution)
        }

        return contributions
    }
}