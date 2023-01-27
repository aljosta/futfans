package com.example.futfans.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.futfans.data.database.models.Country
import com.example.futfans.data.database.models.CountryDao
import com.example.futfans.data.database.models.Team
import com.example.futfans.data.database.models.TeamDao

@Database(entities = [Country::class, Team::class], version = 1)
abstract class SportsDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile private var instance: SportsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SportsDatabase::class.java,
                "sportsdatabase"
            ).build()
    }
}
