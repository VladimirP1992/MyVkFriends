package ru.geekbrains.myvkfriends.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VkFriends(
    @Expose val count: Int,
    @Expose val items: List<VkUser>
): Parcelable