package com.dammak.mahdi.di

import com.dammak.mahdi.database.FavouritesLocalDataSource
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.FavouritesRemoteDataSource
import com.dammak.mahdi.network.FavouritesRemoteDataSourceImp
import com.dammak.mahdi.repository.FavouriteRepository
import com.dammak.mahdi.repository.IFavouriteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindFavouritesLocalDataSource(
        FavouritesLocalDataSourceImp: FavouritesLocalDataSourceImp
    ): FavouritesLocalDataSource

    @Singleton
    @Binds
    abstract fun bindFavouritesRemoteDataSource(
        FavouritesLocalDataSourceImp: FavouritesRemoteDataSourceImp
    ): FavouritesRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindIFavouriteRepository(
        Favourite: FavouriteRepository
    ): IFavouriteRepository


}
