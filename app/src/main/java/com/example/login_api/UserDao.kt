package com.example.login_api

import androidx.room.Insert
import androidx.room.Query

interface UserDao {

    @Query("Select * from userentity")
   suspend fun getAll(): List<UserEntity>

    @Insert
  suspend fun insertUser(vararg userEntity: UserEntity)

}