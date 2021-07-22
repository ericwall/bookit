package com.ericwall.bookit.api.data.repository

import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.api.data.service.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRepository @Inject constructor(private val api: LocationService) {

    fun getLocations(): Flow<List<Location>> {
        return flow {
            // exectute API call and map to UI object
            val locations = api.getLocations()

            println("Eric Locations? $locations")

            // Emit the list to the stream
            emit(locations.locations)
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
    }
}