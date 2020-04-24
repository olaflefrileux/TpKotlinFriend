package com.example.tpfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            this,
            FriendDataBase::class.java,
            "friend_database"
        ).allowMainThreadQueries().build()

        val allFriends = database.friendDao().getAllFriends()
    }
}
