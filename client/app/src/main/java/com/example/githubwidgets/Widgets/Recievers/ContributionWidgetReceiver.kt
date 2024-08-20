package com.example.githubwidgets.Widgets.Recievers

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.example.githubwidgets.Widgets.ContributionWidget

class ContributionWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ContributionWidget()
}