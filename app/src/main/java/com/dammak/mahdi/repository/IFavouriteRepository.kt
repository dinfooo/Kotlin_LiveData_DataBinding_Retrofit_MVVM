package com.dammak.mahdi.repository

import androidx.lifecycle.LiveData
import com.dammak.mahdi.domain.Favourite

interface IFavouriteRepository {
    val favourite: LiveData<List<Favourite>>
    suspend fun refreshFavourite()
}