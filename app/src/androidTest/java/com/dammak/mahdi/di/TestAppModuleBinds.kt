package com.dammak.mahdi.di

import com.dammak.mahdi.FakeAndroidTestFavouriteRepository
import com.dammak.mahdi.database.FavouritesLocalDataSource
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.FavouritesRemoteDataSource
import com.dammak.mahdi.network.FavouritesRemoteDataSourceImp
import com.dammak.mahdi.repository.IFavouriteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * A replacement for [ApplicationModule] to be used in tests. It simply provides a [FakeAndroidTestFavouriteRepository].
 */
@Module
abstract class TestAppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: FakeAndroidTestFavouriteRepository): IFavouriteRepository

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
}
