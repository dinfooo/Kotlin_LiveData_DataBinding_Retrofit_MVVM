package com.dammak.mahdi

import android.app.Application
import android.util.Log
import timber.log.Timber
import timber.log.Timber.DebugTree

class FavouriteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

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