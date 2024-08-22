package com.example.githubwidgets.Workers

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.updateAll
import androidx.work.WorkerParameters
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import com.example.githubwidgets.Widgets.ContributionWidget
import java.util.concurrent.TimeUnit
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.githubwidgets.Storage.AppDatabase
import com.example.githubwidgets.Storage.ColorRepository
import com.example.githubwidgets.apis.ContributionsAPI

class ContributaionWidgetUpdateWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
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

fun updateWidgetManually(context: Context) {
    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniqueWork(
        "ContributionWidgetUpdate",
        ExistingWorkPolicy.KEEP,
        OneTimeWorkRequestBuilder<ContributaionWidgetUpdateWorker>().build()
    )
}

fun scheduleWidgetUpdates(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<ContributaionWidgetUpdateWorker>(15, TimeUnit.MINUTES)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "ContributionWidgetUpdate",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}