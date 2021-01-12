package com.dammak.mahdi.network

import com.dammak.mahdi.domain.Favourite

/**
 * Main entry point for accessing remote favourites data source.
 */
interface FavouritesRemoteDataSource {

    suspend fun getAllFavourites(): List<Favourite>
}
