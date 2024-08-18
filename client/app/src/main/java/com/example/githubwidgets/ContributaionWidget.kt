package com.example.githubwidgets

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.concurrent.TimeUnit

var contributaionGraph = Array(30) { Array(7) { Color(0xff161b22) } }

class ContributaionWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color(0xff0d1117))
                    .padding(0.dp),
                contentAlignment = Alignment.Center
            ) {

                ContributionWidgetLayout(contributaionGraph = contributaionGraph)
            }
        }
    }
}

@Composable
fun ContributionWidgetLayout(contributaionGraph: Array<Array<Color>>) {

    contributaionGraph[1][3] = Color(0xffffffff)

    val squireSpacing = 2.dp
    val squireSize = 10.dp
    val corners = 2.dp

    Row {
        Row {
            for (i in 0..4) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }

        Row {
            for (i in 5..9) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }

        Row {
            for (i in 10..14) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }

        Row {
            for (i in 15..19) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }

        Row {
            for (i in 20..24) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }

        Row {
            for (i in 25..29) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }

                    Column {
                        for (j in 4..6)
                        {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i][j])
                                    .cornerRadius(corners)
                            ) {}

                            Spacer(modifier = GlanceModifier.height(squireSpacing))
                        }
                    }
                }

                Spacer(modifier = GlanceModifier.width(squireSpacing))
            }
        }
    }
}

suspend fun updateMyWidget(context: Context) {
    val glanceAppWidgetManager = GlanceAppWidgetManager(context)
    val glanceIds = glanceAppWidgetManager.getGlanceIds(ContributaionWidget::class.java)
    glanceIds.forEach { glanceId ->
        ContributaionWidget().update(context, glanceId)
    }
}

class ContributaionWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ContributaionWidget()
}

class MyWidgetWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        updateMyWidget(applicationContext)
        return Result.success()
    }
}

fun scheduleWidgetUpdates(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<MyWidgetWorker>(60, TimeUnit.MINUTES)
        .build()
    WorkManager.getInstance(context).enqueue(workRequest)
}