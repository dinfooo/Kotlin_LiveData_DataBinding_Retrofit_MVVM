package com.dammak.mahdi

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSource
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.FavouritesRemoteDataSourceImp
import com.dammak.mahdi.repository.FavouriteRepository
import com.dammak.mahdi.repository.IFavouriteRepository

object ServiceLocator {
    private val lock = Any()

    private var database: AppDatabase? = null

    @Volatile
    var favouriteRepository: IFavouriteRepository? = null
        @VisibleForTesting set

    fun provideFavouritesRepository(context: Context): IFavouriteRepository {
        synchronized(this) {
            return favouriteRepository ?: createFavouritesRepository(context)
        }
    }

    private fun createFavouritesRepository(context: Context): IFavouriteRepository {
        val newRepo =
            FavouriteRepository(
                createFavouritesLocalDataSource(context),
                FavouritesRemoteDataSourceImp()
            )
        favouriteRepository = newRepo
        return newRepo
    }

    private fun createFavouritesLocalDataSource(context: Context): FavouritesLocalDataSource {
        val database = database ?: createDataBase(context)
        return FavouritesLocalDataSourceImp(database.favouriteDao)
    }

    private fun createDataBase(context: Context): AppDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "Favourite.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            favouriteRepository = null
        }
    }
}
