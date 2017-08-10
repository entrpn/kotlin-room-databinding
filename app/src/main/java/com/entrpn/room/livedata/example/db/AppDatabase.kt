package com.entrpn.room.livedata.example.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.entrpn.room.livedata.example.dao.PeopleModelDao
import com.entrpn.room.livedata.example.models.PeopleModel

@Database(entities = arrayOf(PeopleModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    /*//////////////////////////////////////////////////////////
    // ABSTRACT METHODS
    *///////////////////////////////////////////////////////////
    abstract fun peopleModelDao(): PeopleModelDao


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