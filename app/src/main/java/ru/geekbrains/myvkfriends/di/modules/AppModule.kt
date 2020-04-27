package ru.geekbrains.myvkfriends.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.myvkfriends.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}