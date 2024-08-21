package com.example.login_api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(

    @PrimaryKey
    val uid:Int,
    @ColumnInfo("Email")
    val name: String?,
    @ColumnInfo("Password")
    val password: String?
)
