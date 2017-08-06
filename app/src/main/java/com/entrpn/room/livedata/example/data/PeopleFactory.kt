package com.entrpn.room.livedata.example.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeopleFactory {
    /*//////////////////////////////////////////////////////////
    // COMPANION OBJECT
    *///////////////////////////////////////////////////////////
    companion object {
        val BASE_URL: String = "http://api.randomuser.me/"
        val RANDOM_USER_URL = "http://api.randomuser.me/?results=10&nat=en"

        private fun create() : PeopleService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(PeopleService::class.java)
        }
        val peopleService: PeopleService by lazy {
            val peopleService = PeopleFactory.create()
            peopleService
        }
    }

}