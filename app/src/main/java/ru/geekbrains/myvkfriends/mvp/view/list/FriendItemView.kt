package ru.geekbrains.myvkfriends.mvp.view.list

interface FriendItemView {
    var pos: Int
    fun loadAvatar(url: String)
    fun setFirstName(text: String)
    fun setLastName(text: String)

}