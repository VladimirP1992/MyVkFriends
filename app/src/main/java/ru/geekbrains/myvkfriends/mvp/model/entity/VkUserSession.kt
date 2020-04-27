package ru.geekbrains.myvkfriends.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VkUserSession (
    @Expose var clientId: Int,
    @Expose var userId: Int,
    @Expose var accessToken: String,
    @Expose var expiresIn: Int
): Parcelable