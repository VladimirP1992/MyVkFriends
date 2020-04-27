package ru.geekbrains.myvkfriends.navigation

import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser
import ru.geekbrains.myvkfriends.ui.fragment.AuthFragment
import ru.geekbrains.myvkfriends.ui.fragment.FriendFragment
import ru.geekbrains.myvkfriends.ui.fragment.FriendsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class AuthScreen() : SupportAppScreen() {
        override fun getFragment() = AuthFragment.newInstance()
    }

    class FriendsScreen() : SupportAppScreen() {
        override fun getFragment() = FriendsFragment.newInstance()
    }

    class FriendScreen(val user: VkUser) : SupportAppScreen() {
        override fun getFragment() = FriendFragment.newInstance(user)
    }

}