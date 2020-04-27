package ru.geekbrains.myvkfriends.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUserSession

interface IVkUserSessionCache {
    fun getUserSession(): Single<VkUserSession>
    fun putUserSession(userSession: VkUserSession): Completable
}