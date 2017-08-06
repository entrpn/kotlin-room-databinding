package com.entrpn.room.livedata.example

import com.entrpn.room.livedata.example.data.PeopleFactory
import com.entrpn.room.livedata.example.data.PeopleResponse
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun getPeople() {
        PeopleFactory.peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL).enqueue(object: Callback<PeopleResponse> {
            override fun onFailure(call: Call<PeopleResponse>?, t: Throwable?) {
                assertEquals(true,false)
            }
            override fun onResponse(call: Call<PeopleResponse>?, response: Response<PeopleResponse>?) {
                assertEquals(response != null, true)
                response?. let {
                    assertEquals(response.isSuccessful, false)
                    assertEquals(response.body().peopleModelList.size, 9)
                }
            }
        })
    }
}
