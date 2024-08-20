package com.example.githubwidgets.Storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import android.content.Context
import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "colors")
data class ColorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val colorHexValue: String
)

@Dao
interface ColorDao {

    @Insert
    suspend fun insertColor(color: ColorEntity)

    @Query("SELECT colorHexValue FROM colors")
    suspend fun getAllColors(): List<String>

    @Query("DELETE FROM colors")
    suspend fun clearAllColors()
}

@Database(entities = [ColorEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun colorDao(): ColorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class ColorRepository(private val colorDao: ColorDao) {

    suspend fun addColor(colorHexValue: String) {
        colorDao.insertColor(ColorEntity(colorHexValue = colorHexValue))
    }

    suspend fun getColors(): List<Color> {
        val hexColors = colorDao.getAllColors()
        return hexColors.map { hexToColor(it) }
    }

    suspend fun clearColors() {
        colorDao.clearAllColors()
    }


    private fun hexToColor(hex: String): Color {
        return Color(parseColor(hex))
    }
}
