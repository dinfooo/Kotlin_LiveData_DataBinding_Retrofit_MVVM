package com.dammak.mahdi

import android.os.Build
import android.util.Log
import androidx.multidex.MultiDexApplication
import androidx.work.*
import com.dammak.mahdi.work.RefreshDataWorker
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.concurrent.TimeUnit

class FavouriteApplication : MultiDexApplication() {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
        setupRecurringWork()
    }

}

/**
 * Setup WorkManager background job to 'fetch' new network data daily.
 */
private fun setupRecurringWork() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .setRequiresBatteryNotLow(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }
        .build()
    val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
        .setConstraints(constraints)
        .build()
    WorkManager.getInstance().enqueueUniquePeriodicWork(
        RefreshDataWorker.WORK_NAME,
        ExistingPeriodicWorkPolicy.KEEP,
        repeatingRequest
    )
}

class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            // log your crash to your favourite
            // Sending crash report to Firebase CrashAnalytics

            // FirebaseCrash.report(message);
            // FirebaseCrash.report(new Exception(message));
        }
    }
}