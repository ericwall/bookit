package com.ericwall.bookit.api.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ericwall.bookit.api.data.model.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getAllLocations(): List<Location>

    @Insert
    fun insertLocations(vararg location: Location)
}