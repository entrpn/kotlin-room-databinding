package com.entrpn.room.livedata.example.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.entrpn.room.livedata.example.models.LocationModel

@Dao
interface LocationModelDao {

    @Query("select * from LocationModel where userEmail = :p0")
    fun getLocation(userEmail: String): LiveData<List<LocationModel>>

    @Query("select * from LocationModel")
    fun getAllLocations() : LiveData<List<LocationModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocation(locationModel: LocationModel)

    @Delete
    fun deleteLocation(locationModel: LocationModel)
}