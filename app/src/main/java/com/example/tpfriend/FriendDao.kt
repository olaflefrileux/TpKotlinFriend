package com.example.tpfriend

import androidx.room.*

@Dao
interface FriendDao {

    @Query("SELECT * FROM friend")
    fun getAllFriends(): List<Friend>

    @Insert
    fun insertFriend(friend : Friend)

    @Update
    fun updateFriend(friend : Friend)

    @Delete
    fun removeFriend(friend : Friend)

}