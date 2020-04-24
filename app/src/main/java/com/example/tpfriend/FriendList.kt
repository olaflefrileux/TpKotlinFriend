package com.example.tpfriend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class FriendList(friendList: List<Friend>) : RecyclerView.Adapter<FriendList.ViewHolder>()
{
    private var dataset: MutableList<Friend> = friendList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.friend_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = dataset[position]
        holder.fName.setText( friend.firstName )
        holder.fMail.setText( friend.email)

        holder.editButton.setOnClickListener {
            val editFriend = Friend(
                uid = dataset[position].uid,
                firstName = holder.fName.text.toString(),
                rating = 1000,
                email = holder.fMail.text.toString()
            )
            eventListener?.onFriendEdit(editFriend)
        }

        holder.removeButton.setOnClickListener { eventListener?.onFriendDelete(dataset[position]) }
    }

    fun updateData( friendList: List<Friend> ) {
        dataset.clear()
        dataset.addAll(friendList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size

    private var eventListener: EventListener? = null

    fun setEventListener(eventListener: EventListener) {
        this.eventListener = eventListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fName = view.findViewById<EditText>(R.id.friendName)!!
        val fMail = view.findViewById<EditText>(R.id.friendMail)!!
        val editButton = view.findViewById<Button>(R.id.Edit)!!
        val removeButton = view.findViewById<Button>(R.id.Remove)!!
    }

    interface EventListener {
        fun onFriendEdit(friend: Friend)
        fun onFriendDelete(friend: Friend)
    }
}



