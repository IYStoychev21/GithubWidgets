package com.example.githubwidgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class ContributaionWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color(0x000d1117))
                    .padding(0.dp),
                contentAlignment = Alignment.Center
            ) {
                ContributionWidgetLayout()
            }
        }
    }
}

var contributaionGraph = Array<Array<Color>>(7) { Array<Color>(50) {Color(0xff161B22) }};

@Composable
fun ContributionWidgetLayout() {
    val squireSpacing = 1.dp
    val squireSize = 6.dp
    val corners = 1.dp

    contributaionGraph[0][0] = Color(0xff0E4429)
    contributaionGraph[1][0] = Color(0xff0E4429)
    contributaionGraph[6][49] = Color(0xff0E4429)

    Box (
        modifier = GlanceModifier
            .background(Color(0xff0d1117))
            .padding(5.dp)
            .cornerRadius(2.dp)
    )
    {
        Column {
            Column {
                for (i in 0..3) {
                    Row {
                        Row {
                            Row {
                                for (j in 0..4) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 5..9) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 10..14) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 15..19) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 20..24) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 25..29) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 30..34) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 35..39) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 40..44) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 45..48) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }

                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[i][49])
                                        .cornerRadius(corners)
                                ) {}
                            }
                        }
                    }
                    Spacer(modifier = GlanceModifier.height(squireSpacing))
                }
            }

            Column {
                for (i in 4..6) {
                    Row {
                        Row {
                            Row {
                                for (j in 0..4) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 5..9) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 10..14) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 15..19) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 20..24) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 25..29) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 30..34) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 35..39) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }
                        }

                        Row {
                            Row {
                                for (j in 40..44) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }
                            }

                            Row {
                                for (j in 45..48) {
                                    Box(
                                        modifier = GlanceModifier
                                            .size(squireSize)
                                            .background(contributaionGraph[i][j])
                                            .cornerRadius(corners)
                                    ) {}

                                    Spacer(modifier = GlanceModifier.width(squireSpacing))
                                }

                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[i][49])
                                        .cornerRadius(corners)
                                ) {}
                            }
                        }
                    }

                    Spacer(modifier = GlanceModifier.height(squireSpacing))
                }
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
    val workRequest = PeriodicWorkRequestBuilder<MyWidgetWorker>(15, TimeUnit.MINUTES)
        .build()
    WorkManager.getInstance(context).enqueue(workRequest)
}