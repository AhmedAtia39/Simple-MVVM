package com.example.simplemvvm.remote

import android.content.Intent
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitClass {
    lateinit var apiService: ApiService

    private fun createMYRetrofit(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun mService(): ApiService {
        return apiService
    }

    companion object {
        var retrofitClass: RetrofitClass? = null
        var BASE_URL = "https://saaei.co/api/v1/"

        val instance: RetrofitClass
            get() {
                if (retrofitClass == null) {
                    retrofitClass = RetrofitClass()
                    retrofitClass!!.createMYRetrofit(BASE_URL)
                }
                return retrofitClass as RetrofitClass
            }
    }
}
