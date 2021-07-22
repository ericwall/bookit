package com.ericwall.bookit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import androidx.annotation.NonNull

import timber.log.Timber

import timber.log.Timber.DebugTree




@HiltAndroidApp
class BookItApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}