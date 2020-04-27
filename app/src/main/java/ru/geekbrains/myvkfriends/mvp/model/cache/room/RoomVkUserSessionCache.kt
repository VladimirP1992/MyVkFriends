package ru.geekbrains.myvkfriends.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUserSession
import ru.geekbrains.myvkfriends.mvp.model.entity.room.RoomVkUserSession
import ru.geekbrains.myvkfriends.mvp.model.entity.room.db.Database
import java.lang.RuntimeException

class RoomVkUserSessionCache(val database: Database): IVkUserSessionCache {
    override fun getUserSession() = Single.fromCallable {
        return@fromCallable database.userSessionDao.getCurrentSession()?.run { VkUserSession(clientId, userId, accessToken, expiresIn) }
            ?: throw RuntimeException("No session record in cache")
    }.subscribeOn(Schedulers.io())

    override fun putUserSession(userSession: VkUserSession) = Completable.fromAction{
        val roomUserSession = RoomVkUserSession(userSession.clientId, userSession.userId,
            userSession.accessToken, userSession.expiresIn)
        database.userSessionDao.insert(roomUserSession)
    }.subscribeOn(Schedulers.io())
}