package com.entrpn.room.livedata.example.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.entrpn.room.livedata.example.models.PictureModel

@Dao
interface PictureModelDao {

    @Query("select * from PictureModel where userEmail = :p0")
    fun getAllPictures(userEmail: String): LiveData<List<PictureModel>>

    @Query("select * from PictureModel")
    fun getAllPictures() : LiveData<List<PictureModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPicture(pictureModel: PictureModel)

    @Delete
    fun deletePicture(pictureModel: PictureModel)
}