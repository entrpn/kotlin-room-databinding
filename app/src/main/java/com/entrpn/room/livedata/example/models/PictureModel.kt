package com.entrpn.room.livedata.example.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PictureModel(
        @SerializedName("large") var large: String,
        @SerializedName("medium") var medium: String,
        @SerializedName("thumbnail") var thumbnail: String) : Serializable {}