package com.entrpn.room.livedata.example.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class PeopleModel(@PrimaryKey(autoGenerate = true) val _id: Int,
                  @SerializedName("gender") var gender: String,
                  @SerializedName("name") @Embedded var nameModel: NameModel = NameModel("","",""),
                  @SerializedName("location") @Embedded var locationModel: LocationModel = LocationModel("","","",""),
                  @SerializedName("email") var mail: String = "",
                  @SerializedName("login") @Embedded var loginModel: LoginModel = LoginModel(""),
                  @SerializedName("phone") var phone: String = "",
                  @SerializedName("cell") var cell: String = "",
                  @SerializedName("picture") @Embedded var pictureModel: PictureModel = PictureModel("","","")) : Serializable {

    companion object {
        val TAG = "PeopleModel"
    }
}