package com.entrpn.room.livedata.example.data

import com.entrpn.room.livedata.example.models.RetrofitPeopleModel
import com.google.gson.annotations.SerializedName

class PeopleResponse {
    @SerializedName("results") var peopleModelList: List<RetrofitPeopleModel> = ArrayList()
}