package com.demo.userlistdemo.retrofit

import com.demo.userlistdemo.data.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("users")
    fun getUserList(): Call<List<UserModel>>
}