package com.entrpn.room.livedata.example.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PeopleService {
    @GET fun fetchPeople(@Url url: String): Call<PeopleResponse>
}