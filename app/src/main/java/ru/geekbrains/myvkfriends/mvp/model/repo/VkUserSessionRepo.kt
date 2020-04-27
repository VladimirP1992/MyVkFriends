package ru.geekbrains.myvkfriends.mvp.model.repo

import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUserSession

class VkUserSessionRepo(val cache: IVkUserSessionCache) {
    fun getUserSession() = cache.getUserSession()
    fun putUserSession(userSession: VkUserSession) = cache.putUserSession(userSession)
}