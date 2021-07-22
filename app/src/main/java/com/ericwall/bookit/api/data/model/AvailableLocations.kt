package com.ericwall.bookit.api.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableLocations(
    @Json(name = "rooms")
    val locations: List<Location>
)