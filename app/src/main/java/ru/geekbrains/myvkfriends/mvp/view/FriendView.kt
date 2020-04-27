package ru.geekbrains.myvkfriends.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendView : MvpView {
    fun init()
    fun loadAvatar(url: String)
    fun setFirstName(text: String)
    fun setLastName(text: String)
    fun setId(id: Int)
    fun setFollowersCount(followersCount: Int)
}