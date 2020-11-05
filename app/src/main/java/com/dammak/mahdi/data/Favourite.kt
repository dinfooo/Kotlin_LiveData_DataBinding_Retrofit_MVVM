package com.dammak.mahdi.data

import com.squareup.moshi.Json

data class Favourite(
    @Json(name = "created_at") val createdAt: String,
    val id: Int,
    val image: Image
)