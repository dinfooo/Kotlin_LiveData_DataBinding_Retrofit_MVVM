package com.dammak.mahdi

import androidx.lifecycle.MutableLiveData
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.repository.IFavouriteRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class FakeAndroidTestFavouriteRepository @Inject constructor() : IFavouriteRepository {

    private val favouriteServiceData: MutableList<Favourite> = mutableListOf()

    override val favourite: MutableLiveData<List<Favourite>>
        get() = MutableLiveData(favouriteServiceData)

    override suspend fun refreshFavourite() {
        favourite.postValue(favouriteServiceData)
    }

    suspend fun addFavourite(vararg favourites: Favourite) {
        for (favourite in favourites) {
            favouriteServiceData.add(favourite)
        }
        refreshFavourite()
    }

    suspend fun deleteAllFavourite() {
        favouriteServiceData.clear()
        refreshFavourite()
    }
}