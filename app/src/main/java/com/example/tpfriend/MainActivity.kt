package com.example.tpfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var _db: FriendDataBase
    lateinit var _viewFriendList: FriendList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _db = Room.databaseBuilder(
            this,
            FriendDataBase::class.java,
            "friend_database"
        ).allowMainThreadQueries().build()

        val allFriends = _db.friendDao().getAllFriends()
        val viewManager = LinearLayoutManager(this)
        _viewFriendList = FriendList(allFriends)



        _viewFriendList.setEventListener(
            object : FriendList.EventListener{
                override fun onFriendEdit(friend: Friend) {
                    _db.friendDao().updateFriend(friend)
                }

                override fun onFriendDelete(friend: Friend) {
                    _db.friendDao().removeFriend(friend)
                    refreshView()
                }
            }
        )

        findViewById<RecyclerView>(R.id.rvFriend).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = _viewFriendList
        }


    }

    fun refreshView() = _viewFriendList.updateData(_db.friendDao().getAllFriends())

    fun addOne(view: View) {

        var editName = findViewById(R.id.newFName) as EditText
        var editEmail = findViewById(R.id.newFMail) as EditText

        var name = editName.text.toString()

        _db.friendDao().insertFriend(Friend(firstName = name, rating = 1000, email = editEmail.text.toString()))
        refreshView()

        editName.setText("")
        editEmail.setText("")
    }
}
