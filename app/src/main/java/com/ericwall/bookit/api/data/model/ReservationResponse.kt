package com.ericwall.bookit.api.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReservationResponse(
    val success: Boolean
)