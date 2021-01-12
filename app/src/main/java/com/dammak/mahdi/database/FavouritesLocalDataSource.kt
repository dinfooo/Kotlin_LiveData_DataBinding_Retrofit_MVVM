package com.dammak.mahdi.database

import androidx.lifecycle.LiveData
import com.dammak.mahdi.domain.Favourite

/**
 * Data source from db.
 */
class FavouritesLocalDataSource internal constructor(
    private val favouriteDao: FavouriteDao
) {

    fun getFavourites(): LiveData<List<DatabaseFavourite>> {
        return favouriteDao.getFavourites()
    }

    suspend fun insertAll(listFavourite: List<Favourite>) {
        favouriteDao.insertAll(listFavourite.asDatabaseFavourite())
    }

}
