package com.dammak.mahdi.di

import android.content.Context
import androidx.room.Room
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module
object AppModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class TasksRemoteDataSource

    @Qualifier
    @Retention(RUNTIME)
    annotation class TasksLocalDataSource

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavouritesRemoteDataSourceImp(favouritesApiService: FavouritesApiService)
            : FavouritesRemoteDataSourceImp {
        return FavouritesRemoteDataSourceImp(favouritesApiService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavouritesApiService(retrofit: Retrofit)
            : FavouritesApiService {
        return retrofit.create(FavouritesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(FavouritesApiService.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavouritesLocalDataSourceImp(
        database: AppDatabase
    ): FavouritesLocalDataSourceImp {
        return FavouritesLocalDataSourceImp(
            database.favouriteDao
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Favourite.db"
        ).build()
    }
}
