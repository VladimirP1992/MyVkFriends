package ru.geekbrains.myvkfriends.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUsersCache
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser
import ru.geekbrains.myvkfriends.mvp.model.entity.room.RoomVkUser
import ru.geekbrains.myvkfriends.mvp.model.entity.room.db.Database
import java.lang.RuntimeException

class RoomVkUsersCache (val database: Database, val sessionCache: IVkUserSessionCache) : IVkUsersCache {

    override fun getUser(userId: Int) = Single.fromCallable {
        return@fromCallable database.userDao.findById(userId)?.run { VkUser(id, firstName, lastName, photo50, followersCount) }
            ?: throw RuntimeException("No such user in cache")
    }.subscribeOn(Schedulers.io())

    override fun putUser(user: VkUser) = Completable.fromAction {
        val roomUser = database.userDao.findById(user.id)?.apply {
            firstName = user.firstName
            lastName = user.lastName
            photo50 = user.photo_50
            followersCount = user.followersCount

        } ?: RoomVkUser(user.id, user.firstName, user.lastName, user.photo_50, user.followersCount)
        database.userDao.insert(roomUser)
    }.subscribeOn(Schedulers.io())

    override fun getUsers()= Single.fromCallable {
        return@fromCallable database.userDao.getAll().map { roomUser ->
            VkUser(roomUser.id, roomUser.firstName, roomUser.lastName, roomUser.photo50, roomUser.followersCount)
        }
    }.subscribeOn(Schedulers.io())


    override fun putUsers(users: List<VkUser>) = Completable.fromAction {
        val roomUsers = users
            .map { user -> RoomVkUser(user.id, user.firstName, user.lastName, user.photo_50, user.followersCount) }
        database.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}