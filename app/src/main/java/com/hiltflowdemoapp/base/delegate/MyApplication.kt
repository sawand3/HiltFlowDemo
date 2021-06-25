package com.hiltflowdemoapp.base.delegate

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Manjinder Singh on 05,January,2021
 */
@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
        initTimber()
    }

    private fun initTimber() {
//        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
//        }
    }

    companion object {
        lateinit var application: Context
    }
}