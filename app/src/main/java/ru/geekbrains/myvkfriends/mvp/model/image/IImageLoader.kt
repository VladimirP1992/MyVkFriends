package ru.geekbrains.myvkfriends.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}