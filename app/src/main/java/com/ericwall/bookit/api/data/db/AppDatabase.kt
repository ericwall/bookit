package com.ericwall.bookit.api.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericwall.bookit.api.data.model.Location

@Database(entities = [Location::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}