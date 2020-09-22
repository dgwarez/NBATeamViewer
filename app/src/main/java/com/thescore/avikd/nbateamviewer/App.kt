package com.thescore.avikd.nbateamviewer

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.thescore.avikd.nbateamviewer.di.AppInjector
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.SortMethod
import com.thescore.avikd.nbateamviewer.util.CrashReportingTree
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        var sortMethod: SortMethod? = null
    }
}