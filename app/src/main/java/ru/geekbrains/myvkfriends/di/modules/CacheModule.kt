package ru.geekbrains.myvkfriends.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUsersCache
import ru.geekbrains.myvkfriends.mvp.model.cache.room.RoomVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.cache.room.RoomVkUsersCache
import ru.geekbrains.myvkfriends.mvp.model.entity.room.db.Database
import ru.geekbrains.myvkfriends.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database{
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()
    }

    @Provides
    fun userSessionCache(database: Database): IVkUserSessionCache{
        return RoomVkUserSessionCache(database)
    }

    @Provides
    fun usersCache(database: Database, sessionCache: IVkUserSessionCache): IVkUsersCache{
        return RoomVkUsersCache(database, sessionCache)
    }
}