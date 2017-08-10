package com.entrpn.room.livedata.example.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LocationModel(@SerializedName("street") var street: String,
                    @SerializedName("city") var city: String,
                    @SerializedName("state") var state: String,
                    @SerializedName("postcode") var zip: String) : Serializable {
    var address: String = ""
        get() = street + "\n" + city + ", " + state + " " + zip
}