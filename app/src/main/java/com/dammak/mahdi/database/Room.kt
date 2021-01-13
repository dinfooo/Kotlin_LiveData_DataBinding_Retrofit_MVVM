package com.dammak.mahdi.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteDao {
    @Query("select * from databasefavourite")
    fun getFavourites(): LiveData<List<DatabaseFavourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favourites: List<DatabaseFavourite>)
}

@Database(entities = [DatabaseFavourite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val favouriteDao: FavouriteDao
}