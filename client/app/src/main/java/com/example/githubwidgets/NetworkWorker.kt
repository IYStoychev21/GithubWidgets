package com.example.githubwidgets

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class NetworkWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        Log.d("NetworkWorker", "doWork started")

        return withContext(Dispatchers.IO) {
            val client = OkHttpClientInstance.client

            val request = Request.Builder()
                .url("https://github-widgets-api-c9b3bdamdrg0hrg3.germanywestcentral-01.azurewebsites.net/get/contributions/${CredentialManager.getUsername()}")
                .build()

            try {
                val response: Response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseData = response.body?.string() ?: ""
                    val jsonResponse = JSONObject(responseData ?: "")

                    val colors = ContributaionGraph(applicationContext)

                    for (i in 154..363) {
                        when (jsonResponse.getJSONArray("contributions").getJSONObject(i).getInt("level")) {
                            0 -> colors.updateColor(i - 154, Color(0xff161B22))
                            1 -> colors.updateColor(i - 154, Color(0xff0E4429))
                            2 -> colors.updateColor(i - 154, Color(0xff006D32))
                            3 -> colors.updateColor(i - 154, Color(0xff26A641))
                            4 -> colors.updateColor(i - 154, Color(0xff39D353))
                        }
                    }

                    // Update Glance widgets
//                    updateGlanceWidgets(applicationContext)
                    ContributaionWidget().updateAll(applicationContext)

                    Result.success()
                } else {
                    Result.failure()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure()
            }
        }
    }
}

fun scheduleNetworkRequests(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val workRequest = PeriodicWorkRequestBuilder<NetworkWorker>(15, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "NetworkWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
}


fun testScheduleNetworkRequests(context: Context) {
    Log.d("NetworkWorkerTest", "Testing network requests")

    val workRequest = OneTimeWorkRequestBuilder<NetworkWorker>()
        .build()
    WorkManager.getInstance(context).enqueue(workRequest)
}
