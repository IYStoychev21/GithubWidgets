package com.example.githubwidgets.Workers

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.githubwidgets.Storage.AppDatabase
import com.example.githubwidgets.Storage.ColorRepository
import com.example.githubwidgets.Widgets.ContributionWidget
import com.example.githubwidgets.apis.ContributionsAPI
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class NetworkWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val contributaionList = ContributionsAPI.getContributions()

        val database = AppDatabase.getDatabase(applicationContext)
        val colorDao = database.colorDao()
        val colorRepository = ColorRepository(colorDao)

        colorRepository.clearColors()
        for (i in 161..contributaionList.size - 1) {
            when(contributaionList[i].level) {
                0 -> colorRepository.addColor("#161B22")
                1 -> colorRepository.addColor("#0E4429")
                2 -> colorRepository.addColor("#006D32")
                3 -> colorRepository.addColor("#26A641")
                4 -> colorRepository.addColor("#39D353")
            }
        }

        ContributionWidget().updateAll(applicationContext)

        return Result.success()
    }
}

fun scheduleNetworkWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<NetworkWorker>(15, TimeUnit.MINUTES)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "NetworkWorker",
        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
        workRequest
    )
}

fun manualNetworkWorkerUpdate(context: Context) {
    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniqueWork(
        "NetworkWorker",
        ExistingWorkPolicy.KEEP,
        OneTimeWorkRequestBuilder<NetworkWorker>().build()
    )
}
