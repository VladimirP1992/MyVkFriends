package ru.geekbrains.myvkfriends.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendsView : MvpView {
    fun init()
    fun loadAvatar(url: String)
    fun setFirstName(text: String)
    fun updateList()
}