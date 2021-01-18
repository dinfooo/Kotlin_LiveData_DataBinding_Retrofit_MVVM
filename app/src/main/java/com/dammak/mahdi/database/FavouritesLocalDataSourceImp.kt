package com.dammak.mahdi.database

import androidx.lifecycle.LiveData
import com.dammak.mahdi.domain.Favourite
import javax.inject.Inject

/**
 * Concrete implementation of local data source .
 */
class FavouritesLocalDataSourceImp internal constructor(
    private val favouriteDao: FavouriteDao
) : FavouritesLocalDataSource {

    override fun getFavourites(): LiveData<List<DatabaseFavourite>> {
        return favouriteDao.getFavourites()
    }

    override suspend fun insertAll(listFavourite: List<Favourite>) {
        favouriteDao.insertAll(listFavourite.asDatabaseFavourite())
    }

}
