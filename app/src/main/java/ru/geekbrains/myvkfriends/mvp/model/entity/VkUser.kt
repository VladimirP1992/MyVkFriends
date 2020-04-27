package ru.geekbrains.myvkfriends.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VkUser(
    @Expose val id: Int,
    @Expose val firstName: String,
    @Expose val lastName: String,
    @Expose val photo_50: String,
    @Expose val followersCount: Int
) : Parcelable