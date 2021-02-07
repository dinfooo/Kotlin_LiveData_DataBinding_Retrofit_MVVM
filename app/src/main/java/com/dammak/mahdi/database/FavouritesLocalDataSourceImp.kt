package com.dammak.mahdi.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.util.formatDateTime
import javax.inject.Inject

/**
 * Concrete implementation of local data source .
 */
class FavouritesLocalDataSourceImp internal constructor(
    private val favouriteDao: FavouriteDao
) : FavouritesLocalDataSource {

    override fun getFavourites(): LiveData<List<DatabaseFavourite>> {
        return Transformations.map(favouriteDao.getFavourites()) { favouritesList ->
            favouritesList.map { item ->
                DatabaseFavourite(item.createdAt.formatDateTime(), item.id, item.image)
            }
        }
    }

    override suspend fun insertAll(listFavourite: List<Favourite>) {
        favouriteDao.insertAll(listFavourite.asDatabaseFavourite())
    }

}
