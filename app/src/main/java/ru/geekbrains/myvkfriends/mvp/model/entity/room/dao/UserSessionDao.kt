package ru.geekbrains.myvkfriends.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.myvkfriends.mvp.model.entity.room.RoomVkUserSession

@Dao
interface UserSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentSessionInfoVk: RoomVkUserSession)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg currentSessionInfoVk: RoomVkUserSession)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentSessionsInfo: List<RoomVkUserSession>)

    @Update
    fun update(currentSessionInfoVk: RoomVkUserSession)

    @Update
    fun update(vararg currentSessionInfoVk: RoomVkUserSession)

    @Update
    fun update(currentSessionsInfo: List<RoomVkUserSession>)

    @Delete
    fun delete(currentSessionInfoVk: RoomVkUserSession)

    @Delete
    fun delete(vararg currentSessionInfoVk: RoomVkUserSession)

    @Delete
    fun delete(currentSessionsInfo: List<RoomVkUserSession>)

    @Query("SELECT * FROM RoomVkUserSession LIMIT 1")
    fun getCurrentSession(): RoomVkUserSession?
}