package com.entrpn.room.livedata.example.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RetrofitPeopleModel(@SerializedName("gender") var gender: String,
                          @SerializedName("name") var name: NameModel = NameModel("","","",""),
                          @SerializedName("location") var location: LocationModel = LocationModel("","","","",""),
                          @SerializedName("email") var mail: String = "",
                          @SerializedName("login") var userName: LoginModel = LoginModel("",""),
                          @SerializedName("phone") var phone: String = "",
                          @SerializedName("cell") var cell: String = "",
                          @SerializedName("picture") var pictures: PictureModel = PictureModel("","","","")) : Serializable {

    /*//////////////////////////////////////////////////////////
    // PUBLIC METHODS
    *///////////////////////////////////////////////////////////
    fun getPeopleModel(): PeopleModel {
        return PeopleModel(mail,gender,phone,cell)
    }
    fun getPictureModel(): PictureModel {
        return PictureModel(mail,pictures.large,pictures.medium,pictures.thumbnail)
    }
    fun getNameModel(): NameModel {
        return NameModel(mail,name.title,name.first,name.last)
    }
    fun getLoginModel(): LoginModel {
        return LoginModel(mail,userName.userName)
    }
    fun getLocationModel(): LocationModel {
        return LocationModel(mail,location.street,location.city,location.state,location.zip)
    }

}