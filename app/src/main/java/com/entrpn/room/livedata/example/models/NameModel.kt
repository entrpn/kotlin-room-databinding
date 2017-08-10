package com.entrpn.room.livedata.example.models

import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NameModel(@SerializedName("title") var title: String,
                @SerializedName("first") var first: String,
                @SerializedName("last") var last: String) : Serializable {

    @Ignore
    var fullName: String = ""
        get() = title + ". " + first + " " + last

}