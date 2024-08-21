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

class ContributaionWidgetUpdateWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        manualNetworkWorkerUpdate(applicationContext)
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
    val workRequest = PeriodicWorkRequestBuilder<ContributaionWidgetUpdateWorker>(1, TimeUnit.HOURS)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "ContributionWidgetUpdate",
        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
        workRequest
    )
}