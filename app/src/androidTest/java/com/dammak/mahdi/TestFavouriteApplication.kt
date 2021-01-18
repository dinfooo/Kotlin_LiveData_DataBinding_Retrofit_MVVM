package com.dammak.mahdi

import com.dammak.mahdi.di.AppComponent
import com.dammak.mahdi.di.DaggerTestAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class TestFavouriteApplication : FavouriteApplication() {

    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
