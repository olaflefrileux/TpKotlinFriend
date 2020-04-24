package com.example.tpfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var _db: FriendDataBase

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
        val viewFriendList = FriendList(allFriends)

        fun refreshView() = viewFriendList.updateData(_db.friendDao().getAllFriends())

        viewFriendList.setEventListener(
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
            adapter = viewFriendList
        }
    }

    fun addOne(view: View) {

        /*
        var editName = findViewById(R.id.FriendName) as EditText
        var editEmail = findViewById(R.id.FriendMail) as EditText

        var name = editName.text.toString()

        _db.friendDao().insertFriend(Friend(firstName = name, rating = 1000, email = editEmail.text.toString()))
        friendsTextView.append("\n" + name)

        editName.setText("")
        editEmail.setText("")

         */
    }
}
