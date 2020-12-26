package com.dammak.mahdi.database

import android.content.Context
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "favourite"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}