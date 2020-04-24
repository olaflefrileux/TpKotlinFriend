package com.example.tpfriend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val firstName: String,
    val rating: Int,
    val email: String
)


