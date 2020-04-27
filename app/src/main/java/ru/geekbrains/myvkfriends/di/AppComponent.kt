package ru.geekbrains.myvkfriends.di

import dagger.Component
import ru.geekbrains.myvkfriends.di.modules.*
import ru.geekbrains.myvkfriends.mvp.presenter.AuthPresenter
import ru.geekbrains.myvkfriends.mvp.presenter.FriendPresenter
import ru.geekbrains.myvkfriends.mvp.presenter.FriendsPresenter
import ru.geekbrains.myvkfriends.mvp.presenter.MainPresenter
import ru.geekbrains.myvkfriends.ui.activity.MainActivity
import ru.geekbrains.myvkfriends.ui.adapter.FriendsRVAdapter
import ru.geekbrains.myvkfriends.ui.fragment.AuthFragment
import ru.geekbrains.myvkfriends.ui.fragment.FriendFragment
import ru.geekbrains.myvkfriends.ui.fragment.FriendsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ImageModule::class,
        ApiModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(authPresenter: AuthPresenter)
    fun inject(authFragment: AuthFragment)

    fun inject(friendsPresenter: FriendsPresenter)
    fun inject(friendsFragment: FriendsFragment)
    fun inject(friendsRVAdapter: FriendsRVAdapter)

    fun inject(FriendPresenter: FriendPresenter)
    fun inject(friendFragment: FriendFragment)
}