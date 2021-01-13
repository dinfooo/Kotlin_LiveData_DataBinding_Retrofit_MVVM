package com.dammak.mahdi.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dammak.mahdi.database.DatabaseFavourite
import com.dammak.mahdi.database.FavouritesLocalDataSource
import com.dammak.mahdi.database.asDatabaseFavourite
import com.dammak.mahdi.domain.Favourite

class FakeFavouritesLocalDataSource(
    var favourites: MutableList<DatabaseFavourite>? = mutableListOf()
) : FavouritesLocalDataSource {
    override fun getFavourites(): LiveData<List<DatabaseFavourite>> {
        return MutableLiveData(favourites)
    }

    override suspend fun insertAll(listFavourite: List<Favourite>) {
        favourites.let {
            it?.clear()
            it?.addAll(listFavourite.asDatabaseFavourite())
        }
    }
}
