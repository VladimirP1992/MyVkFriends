package ru.geekbrains.myvkfriends.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomVkUserSession (
    @PrimaryKey var clientId: Int,
    var userId: Int,
    var accessToken: String,
    var expiresIn: Int
)