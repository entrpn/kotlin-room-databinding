package com.entrpn.room.livedata.example.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.entrpn.room.livedata.example.models.LoginModel

@Dao
interface LoginModelDao {

    @Query("select * from LoginModel where userEmail = :p0")
    fun getLogin(userEmail: String): LiveData<List<LoginModel>>

    @Query("select * from LoginModel")
    fun getAllLogins() : LiveData<List<LoginModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLogin(loginModel: LoginModel)

    @Delete
    fun deleteLogin(loginModel: LoginModel)
}