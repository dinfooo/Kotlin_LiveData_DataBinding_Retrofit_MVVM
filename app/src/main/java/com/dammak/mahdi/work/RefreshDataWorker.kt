package com.dammak.mahdi.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.network.Api
import com.dammak.mahdi.repository.FavouriteRepository
import retrofit2.HttpException
import timber.log.Timber


class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.dammak.mahdi.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = AppDatabase.getInstance(applicationContext)
        val repository = FavouriteRepository(FavouritesLocalDataSourceImp(database.favouriteDao),
            Api.retrofitService)

        try {
            repository.refreshFavourite()
            Timber.d("Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}