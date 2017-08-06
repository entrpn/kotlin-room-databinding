package com.entrpn.room.livedata.example.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class PeopleModel(@PrimaryKey var mail: String,
                       var gender: String,
                       var phone: String,
                       var cell: String) : Serializable