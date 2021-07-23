package com.ericwall.bookit.api.data.repository

import com.ericwall.bookit.api.data.db.LocationDao
import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.api.data.service.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRepository @Inject constructor(private val api: LocationService, private val locationDao: LocationDao) {

    fun getLocations(): Flow<List<Location>> {
        return flow {
            val locations = api.getLocations()
            locationDao.deleteSaveLocations()
            locationDao.insertLocations(*locations.locations.toTypedArray())
            emit(locations.locations)
        }.flowOn(Dispatchers.IO)
    }

    fun getSavedLocations(): Flow<List<Location>> {
        return flow {
            emit(locationDao.getAllLocations())
        }.flowOn(Dispatchers.IO)
    }
}