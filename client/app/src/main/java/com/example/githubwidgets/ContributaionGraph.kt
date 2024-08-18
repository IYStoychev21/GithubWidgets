package com.example.githubwidgets

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.ViewModel
import kotlin.math.log


class ContributaionGraph(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("colors_prefs", Context.MODE_PRIVATE)

    private fun colorToHex(color: Color): String {
        return String.format("#%06X", 0xFFFFFF and color.toArgb())
    }

    private fun hexToColor(hex: String): Color {
        return Color(android.graphics.Color.parseColor(hex))
    }

    fun initializeColors() {
        val defaultColor = "#161b22" // Example default color
        val colorsArray = Array(210) { defaultColor } // Initialize array with default color
        saveColors(colorsArray)
    }

    fun saveColors(colors: Array<String>) {
        with(prefs.edit()) {
            for (i in colors.indices) {
                putString("color_$i", colors[i])
            }
            apply()
        }
    }

    fun getColors(): Array<Color> {
        val colorsArray = Array(210) { "#161b22" }
        for (i in colorsArray.indices) {
            colorsArray[i] = prefs.getString("color_$i", "#161b22") ?: "#161b22"
        }
        return colorsArray.map { hexToColor(it) }.toTypedArray()
    }

    fun updateColor(index: Int, newColor: Color) {
        val colors = getColors().map { colorToHex(it) }.toTypedArray()
        if (index in colors.indices) {
            colors[index] = colorToHex(newColor)
            saveColors(colors)
        }
    }
}