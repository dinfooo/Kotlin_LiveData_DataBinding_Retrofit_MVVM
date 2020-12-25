package com.dammak.mahdi.domain

import com.squareup.moshi.Json

data class Favourite(
    @Json(name = "created_at") val createdAt: String,
    val id: Int,
    val image: Image
)