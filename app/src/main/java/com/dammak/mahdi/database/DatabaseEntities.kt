package com.dammak.mahdi.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.domain.Image

/**
 * Database entities go in this file. These are responsible for reading and writing from the
 * database.
 */

/**
 * DatabaseFavourite represents a favourite entity in the database.
 */
@Entity
data class DatabaseFavourite constructor(
    val createdAt: String,
    @PrimaryKey
    val id: Int,
    @Embedded
    val image: Image
)

/**
 * Map DatabaseFavourite to domain entities
 */
fun List<DatabaseFavourite>.asDomainModel(): List<Favourite> {
    return map {
        Favourite(
            createdAt = it.createdAt,
            id = it.id,
            image = it.image
        )
    }
}

/**
 * Map domain entities to DatabaseFavourite
 */
fun List<Favourite>.asDatabaseFavourite(): List<DatabaseFavourite> {
    return map {
        DatabaseFavourite(
            createdAt = it.createdAt,
            id = it.id,
            image = it.image
        )
    }
}


