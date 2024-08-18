package com.example.githubwidgets

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class ContributaionWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color(0xff0d1117))
                    .padding(10.dp)
            ) {
                ContributionWidgetLayout()
            }
        }
    }
}

var contributaionGraph = Array<String>(371) { "0xff161B22" };

@Composable
fun ContributionWidgetLayout() {
    for (i in contributaionGraph) {
        println(i)
    }
//    Row() {
//        for (i in 0..9) {
//            Box(
//                modifier = GlanceModifier.width(10.dp).height(10.dp)
//                    .background(Color(0xffffffff))
//            ) {}
//        }
//    }
}

class ContributaionWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ContributaionWidget()
}