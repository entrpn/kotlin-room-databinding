package com.entrpn.room.livedata.example.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(foreignKeys = arrayOf(ForeignKey(entity = PeopleModel::class,parentColumns = arrayOf("mail"), childColumns = arrayOf("userEmail"), onDelete = CASCADE)))
class PictureModel(@PrimaryKey() var userEmail: String,
                   @SerializedName("large") var large: String,
                   @SerializedName("medium") var medium: String,
                   @SerializedName("thumbnail") var thumbnail: String) : Serializable {}