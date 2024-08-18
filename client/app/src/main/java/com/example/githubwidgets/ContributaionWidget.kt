package com.example.githubwidgets

import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.glance.appwidget.updateAll
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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.concurrent.TimeUnit


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
                val colorRepository = ContributaionGraph(context)
                val colors = colorRepository.getColors()

                ContributionWidgetLayout(colors)
            }
        }
    }
}

@Composable
fun ContributionWidgetLayout(contributaionGraph: Array<Color>) {
    val squireSpacing = 1.5.dp
    val squireSize = 10.dp
    val corners = 2.dp
    val rowLength = 7

    Row {
        Row {
            for (i in 0..4) {
                Column {
                    Column {
                        for (j in 0..3) {
                            Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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
                                    .background(contributaionGraph[i * rowLength + j])
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

class ContributaionWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ContributaionWidget()
}

class MyWidgetWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        ContributaionWidget().updateAll(applicationContext)
        return Result.success()
    }
}

fun scheduleWidgetUpdates(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<MyWidgetWorker>(60, TimeUnit.MINUTES)
        .build()
    WorkManager.getInstance(context).enqueue(workRequest)
}