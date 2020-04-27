package ru.geekbrains.myvkfriends.ui

import android.app.Application
import ru.geekbrains.myvkfriends.di.AppComponent
import ru.geekbrains.myvkfriends.di.DaggerAppComponent
import ru.geekbrains.myvkfriends.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}