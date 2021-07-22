package com.ericwall.bookit.api.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Location(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val spots: Int,
    @Json(name = "thumbnail")
    val imageUrl: String
)
