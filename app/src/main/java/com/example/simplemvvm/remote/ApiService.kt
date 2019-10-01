package com.example.simplemvvm.remote

import com.example.simplemvvm.home.modelJson.CitiesModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


 interface ApiService
{
    @POST("login")
     fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Call<ResponseBody>

    @GET("cities")
     fun get_cities(): Call<CitiesModel>
}