package com.ericwall.bookit.api.data.service

import com.ericwall.bookit.api.data.model.AvailableLocations
import com.ericwall.bookit.api.data.model.Location
import com.ericwall.bookit.api.data.model.ReservationResponse
import retrofit2.http.GET

interface LocationService {

    @GET("/rooms.json")
    suspend fun getLocations(): AvailableLocations

    @GET("/bookRoom.json")
    suspend fun reserveLocation(): ReservationResponse
}