package com.entrpn.room.livedata.example.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.entrpn.room.livedata.example.models.PeopleModel

@Dao
interface PeopleModelDao {

    @Query("select * from PeopleModel where _id = :p0")
    fun getAllPeople(_id: Int): LiveData<List<PeopleModel>>

    @Query("select * from PeopleModel")
    fun getAllPeople(): LiveData<List<PeopleModel>>

    @Insert(onConflict = REPLACE)
    fun addPeople(peopleModel: PeopleModel)

    @Delete
    fun deletePeople(peopleModel: PeopleModel)
}