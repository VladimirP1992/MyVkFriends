package ru.geekbrains.myvkfriends.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomVkUser(
    @PrimaryKey val id: Int,
    var firstName: String,
    var lastName: String,
    var photo50: String,
    var followersCount: Int
)