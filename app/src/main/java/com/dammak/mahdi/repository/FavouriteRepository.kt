package com.dammak.mahdi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.asDatabaseFavourite
import com.dammak.mahdi.database.asDomainModel
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.network.FavouritesApi

/**
 * Repository for fetching favourite from the network and storing them on disk
 */
class FavouriteRepository(private val database: AppDatabase) {
    val favourite: LiveData<List<Favourite>> =
        Transformations.map(database.favouriteDao.getFavourites()) {
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
    suspend fun refreshFavourite() {
        val listFavourite = FavouritesApi.retrofitService.getAllFavourites()
        Log.d(FavouriteRepository::class.simpleName, "Favourites list from WS :" + listFavourite.toString())
        database.favouriteDao.insertAll(listFavourite.asDatabaseFavourite())
    }
}
