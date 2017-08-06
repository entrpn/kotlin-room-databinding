package com.entrpn.room.livedata.example.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(foreignKeys = arrayOf(ForeignKey(entity = PeopleModel::class,parentColumns = arrayOf("mail"), childColumns = arrayOf("userEmail"), onDelete = ForeignKey.CASCADE)))
class LoginModel(@PrimaryKey var userEmail: String, @SerializedName("username") var userName: String) : Serializable