package com.example.login_api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.POST

interface RetrofitApi{

 @GET("users/2")
 fun getUser(): Call<User>

 @POST("login")
 fun login(): Call<LoginModel>
}