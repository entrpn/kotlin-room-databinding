package com.entrpn.room.livedata.example.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.entrpn.room.livedata.example.dao.*
import com.entrpn.room.livedata.example.models.*

@Database(entities = arrayOf(PeopleModel::class, PictureModel::class, NameModel::class, LoginModel::class, LocationModel::class), version = 5)
abstract class AppDatabase : RoomDatabase() {

    /*//////////////////////////////////////////////////////////
    // ABSTRACT METHODS
    *///////////////////////////////////////////////////////////
    abstract fun peopleModelDao(): PeopleModelDao
    abstract fun pictureModelDao(): PictureModelDao
    abstract fun nameModelDao(): NameModelDao
    abstract fun loginModelDao(): LoginModelDao
    abstract fun locationModelDao(): LocationModelDao


    /*//////////////////////////////////////////////////////////
    // COMPANION OBJECT
    *///////////////////////////////////////////////////////////
    companion object {
        val DB_NAME = "people_db"
        var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase? {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
            }
            return dbInstance
        }
    }

}