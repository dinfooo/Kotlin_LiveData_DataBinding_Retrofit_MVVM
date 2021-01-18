package com.dammak.mahdi.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dammak.mahdi.ServiceLocator
import com.dammak.mahdi.repository.IFavouriteRepository
import retrofit2.HttpException
import timber.log.Timber


class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    val repository: IFavouriteRepository
        get() = ServiceLocator.provideFavouritesRepository(applicationContext)

    companion object {
        const val WORK_NAME = "com.dammak.mahdi.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        try {
            repository.refreshFavourite()
            Timber.d("Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}