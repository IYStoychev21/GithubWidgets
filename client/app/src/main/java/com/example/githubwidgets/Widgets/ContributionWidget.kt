package com.example.githubwidgets.Widgets

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
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
import androidx.glance.state.GlanceStateDefinition
import com.example.githubwidgets.Storage.AppDatabase
import com.example.githubwidgets.Storage.ColorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.io.File

class ContributionWidgetStorage(private val context: Context) : DataStore<List<Color>>{
    override val data: Flow<List<Color>>
        get() {
            val database = AppDatabase.getDatabase(context)
            val colorDao = database.colorDao()
            val colorRepository = ColorRepository(colorDao)

            return flow { emit(colorRepository.getColors()) }
        }

    override suspend fun updateData(transform: suspend (t: List<Color>) -> List<Color>): List<Color> {
        TODO("Not yet implemented")
    }
}

class ContributionWidget : GlanceAppWidget() {
    override val stateDefinition: GlanceStateDefinition<List<Color>>
        get() = object: GlanceStateDefinition<List<Color>> {
            override suspend fun getDataStore(
                context: Context,
                fileKey: String
            ): DataStore<List<Color>> {
                return ContributionWidgetStorage(context)
            }

            override fun getLocation(context: Context, fileKey: String): File {
                TODO("Not yet implemented")
            }
        }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color(0xff0d1117))
                    .padding(0.dp),
                contentAlignment = Alignment.Center
            ) {
                Graph(currentState())
            }
        }
    }
}

@Composable
fun Graph(contributaionGraph: List<Color>) {
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
            for (i in 25..28) {
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
                        for (j in 4..6) {
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

            val extraDays = (contributaionGraph.size + 161) - 364
            if (extraDays > 0) {
                Row {
                    Column {
                        Column {
                            if (extraDays >= 1) {
                                Box(
                                modifier = GlanceModifier
                                    .size(squireSize)
                                    .background(contributaionGraph[29 * rowLength + 0])
                                    .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }

                            if (extraDays >= 2) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 1])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }

                            if (extraDays >= 3) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 2])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }
                        }

                        Column {
                            if (extraDays >= 4) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 3])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }

                            if (extraDays >= 5) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 4])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }

                            if (extraDays >= 6) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 5])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }

                            if (extraDays >= 7) {
                                Box(
                                    modifier = GlanceModifier
                                        .size(squireSize)
                                        .background(contributaionGraph[29 * rowLength + 6])
                                        .cornerRadius(corners)
                                ) {}
                                Spacer(modifier = GlanceModifier.height(squireSpacing))
                            }
                        }
                    }
                }
            }
        }
    }
}