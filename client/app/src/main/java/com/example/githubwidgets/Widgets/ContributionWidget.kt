package com.example.githubwidgets.Widgets

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
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
            Graph(data = currentState())
        }
    }
}

@Composable
fun Graph(data: List<Color>) {
    Log.d("Contributaion Widget", data.toString())
}