package ru.geekbrains.myvkfriends.mvp.model.entity

import com.google.gson.annotations.Expose

class Response<T> (
    @Expose val response: T
)