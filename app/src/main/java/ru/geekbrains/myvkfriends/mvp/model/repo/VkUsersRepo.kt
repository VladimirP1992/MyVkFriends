package ru.geekbrains.myvkfriends.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.myvkfriends.mvp.model.api.IVkApi
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUsersCache
import ru.geekbrains.myvkfriends.mvp.model.network.INetworkStatus

class VkUsersRepo (val api: IVkApi, val networkStatus: INetworkStatus,
                   val userCache: IVkUsersCache, val sessionCache: IVkUserSessionCache) {

    fun getUser(token: String, userId: Int) = networkStatus.isOnlineSingle().flatMap {isOnline ->
        if(isOnline){
            api.getUser(token, userId)
                .flatMap {
                    it.response?.let{users ->
                        users[0]?.let {user ->
                            return@flatMap userCache.putUser(user).toSingleDefault(user)
                        }
                    }
                }
        } else {
            userCache.getUser(userId)
        }
    }.subscribeOn(Schedulers.io())


    fun getFriends() = networkStatus.isOnlineSingle().flatMap {isOnline ->
        if(isOnline){
            sessionCache.getUserSession()
                .flatMap {
                    api.getFriends(it.accessToken, it.userId)
                        .flatMap {
                            it.response.let{
                                it.items.let {users ->
                                    return@flatMap userCache.putUsers(users).toSingleDefault(users)
                                }
                            }
                        }
                }
        } else {
            userCache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}