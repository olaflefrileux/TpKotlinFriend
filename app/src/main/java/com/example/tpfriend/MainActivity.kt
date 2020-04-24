package com.example.tpfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        allFriends.forEach {
            friendsTextView.append("\n" + it.firstName)
        }
    }

    fun addOne(view: View) {

        var editName = findViewById(R.id.FriendName) as EditText
        var editEmail = findViewById(R.id.FriendMail) as EditText

        var name = editName.text.toString()

        _db.friendDao().insertFriend(Friend(firstName = name, rating = 1000, email = editEmail.text.toString()))
        friendsTextView.append("\n" + name)

        editName.setText("")
        editEmail.setText("")
    }
}
