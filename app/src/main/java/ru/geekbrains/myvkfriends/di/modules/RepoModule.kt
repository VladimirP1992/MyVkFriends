package ru.geekbrains.myvkfriends.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.myvkfriends.mvp.model.api.IVkApi
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUserSessionCache
import ru.geekbrains.myvkfriends.mvp.model.cache.IVkUsersCache
import ru.geekbrains.myvkfriends.mvp.model.network.INetworkStatus
import ru.geekbrains.myvkfriends.mvp.model.repo.VkUserSessionRepo
import ru.geekbrains.myvkfriends.mvp.model.repo.VkUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun userSessionRepo(cache: IVkUserSessionCache): VkUserSessionRepo{
        return VkUserSessionRepo(cache)
    }

    @Singleton
    @Provides
    fun usersRepo(
        api: IVkApi, networkStatus: INetworkStatus,
        userCache: IVkUsersCache, sessionCache: IVkUserSessionCache
    ): VkUsersRepo{
        return VkUsersRepo(api, networkStatus, userCache, sessionCache)
    }
}