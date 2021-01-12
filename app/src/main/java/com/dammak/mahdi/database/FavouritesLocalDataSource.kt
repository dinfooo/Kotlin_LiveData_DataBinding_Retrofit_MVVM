package com.dammak.mahdi.database

import androidx.lifecycle.LiveData
import com.dammak.mahdi.domain.Favourite

/**
 * Main entry point for accessing local favourites data source.
 */
interface FavouritesLocalDataSource {

    fun getFavourites(): LiveData<List<DatabaseFavourite>>
    suspend fun insertAll(listFavourite: List<Favourite>)
}
