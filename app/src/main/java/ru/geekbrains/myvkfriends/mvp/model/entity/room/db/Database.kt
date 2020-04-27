package ru.geekbrains.myvkfriends.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.geekbrains.myvkfriends.mvp.model.entity.room.RoomVkUserSession
import ru.geekbrains.myvkfriends.mvp.model.entity.room.RoomVkUser
import ru.geekbrains.myvkfriends.mvp.model.entity.room.dao.UserDao
import ru.geekbrains.myvkfriends.mvp.model.entity.room.dao.UserSessionDao


@androidx.room.Database(
    entities = [
        RoomVkUser::class,
        RoomVkUserSession::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val userSessionDao: UserSessionDao

    companion object {
        const val DB_NAME = "database.db"
    }
}