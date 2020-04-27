package ru.geekbrains.myvkfriends.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser

interface IVkUsersCache {
    fun getUser(id: Int): Single<VkUser>
    fun putUser(user: VkUser): Completable

    fun getUsers(): Single<List<VkUser>>
    fun putUsers(users: List<VkUser>): Completable
}