package com.dammak.mahdi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dammak.mahdi.database.FavouritesLocalDataSource
import com.dammak.mahdi.database.asDomainModel
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.network.FavouritesRemoteDataSource
import timber.log.Timber

/**
 * Repository for fetching favourite from the network and storing them on disk
 */
class FavouriteRepository(
    private val favouritesLocalDataSource: FavouritesLocalDataSource,
    private val favouritesRemoteDataSource: FavouritesRemoteDataSource
) : IFavouriteRepository {

    override val favourite: LiveData<List<Favourite>> =
        Transformations.map(favouritesLocalDataSource.getFavourites()) {
            it.asDomainModel()
        }

    /*
     * Don't need to moves the execution of the coroutine
     * to an I/O thread "withContext(Dispatchers.IO)"
     *
     * In fact, both Room and Retrofit make suspending functions main-safe. It's safe to call these
     * suspend funs from Dispatchers.Main, even though they fetch from the network and write
     * to the database.
     *
     * link :
     * https://stackoverflow.com/questions/60963194/what-does-main-safe-in-kotlin-coroutines
     */
    override suspend fun refreshFavourite() {
        val listFavourite = favouritesRemoteDataSource.getAllFavourites()
        Timber.d("Favourites list from WS :" + listFavourite.toString())
        favouritesLocalDataSource.insertAll(listFavourite)
    }
}
