package com.entrpn.room.livedata.example.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginModel(@SerializedName("username") var userName: String) : Serializable