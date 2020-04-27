package ru.geekbrains.myvkfriends.mvp.presenter.list

import ru.geekbrains.myvkfriends.mvp.view.list.FriendItemView

interface IFriendListPresenter {
    var itemClickListener: ((FriendItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: FriendItemView)
}