package com.dammak.mahdi.util

import com.dammak.mahdi.FakeAndroidTestFavouriteRepository
import com.dammak.mahdi.domain.Favourite
import kotlinx.coroutines.runBlocking

/**
 * A blocking version of FakeAndroidTestFavouriteRepository.addFavourite to minimize the number of
 * times we have to explicitly add <code>runBlocking { ... }</code> in our tests
 */
fun FakeAndroidTestFavouriteRepository.addFavouriteBlocking(vararg favourites: Favourite) = runBlocking {
    this@addFavouriteBlocking.addFavourite(*favourites)
}

fun FakeAndroidTestFavouriteRepository.deleteAllFavouriteBlocking() = runBlocking {
    this@deleteAllFavouriteBlocking.deleteAllFavourite()
}


