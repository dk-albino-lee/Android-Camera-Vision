package com.dongkeun.camerawithvision

import android.app.Application
import timber.log.Timber

class ThisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}