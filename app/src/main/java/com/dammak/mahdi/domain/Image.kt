package com.dammak.mahdi.domain

import androidx.room.ColumnInfo

data class Image(
    @ColumnInfo(name = "image_id")
    val id: String,
    val url: String?
)