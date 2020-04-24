package com.example.tpfriend

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1)
abstract class FriendDataBase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}