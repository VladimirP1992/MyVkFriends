package ru.geekbrains.myvkfriends.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.myvkfriends.mvp.model.entity.Response
import ru.geekbrains.myvkfriends.mvp.model.entity.VkFriends
import ru.geekbrains.myvkfriends.mvp.model.entity.VkUser

interface IVkApi {
    @GET("/method/users.get")
    fun getUser(
        @Query("access_token") accessToken : String,
        @Query("user_ids") userIds: Int,
        @Query("fields") fields: String = "photo_50,followers_count",
        @Query("v") v: String = Constants.apiVersion
    ): Single<Response<List<VkUser>?>>

    @GET("/method/friends.get")
    fun getFriends(
        @Query("access_token") accessToken : String,
        @Query("user_id") userId : Int,
        @Query("order") order : String = "name",
        @Query("fields") fields : String = "photo_50",
        @Query("v") v: String = Constants.apiVersion
    ):Single<Response<VkFriends>>

}