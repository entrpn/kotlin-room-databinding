package com.entrpn.room.livedata.example.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.entrpn.room.livedata.example.models.NameModel

@Dao
interface NameModelDao {
    @Query("select * from NameModel where userEmail = :p0")
    fun getAllNames(userEmail: String): LiveData<List<NameModel>>

    @Query("select * from NameModel")
    fun getAllNames() : LiveData<List<NameModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addName(nameModel: NameModel)

    @Delete
    fun deleteName(nameModel: NameModel)
}