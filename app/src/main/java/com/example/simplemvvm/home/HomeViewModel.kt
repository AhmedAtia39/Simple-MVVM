package com.example.simplemvvm.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.simplemvvm.home.modelJson.CitiesData
import com.example.simplemvvm.home.modelJson.CitiesModel
import com.example.simplemvvm.remote.ApiService
import com.example.simplemvvm.remote.RetrofitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel()
{
    var viewState:MutableLiveData<ArrayList<CitiesData>> = MutableLiveData()
    lateinit var service:ApiService

    fun getCities()
    {
        service = RetrofitClass.instance.apiService

        service.get_cities().enqueue(object :  Callback<CitiesModel> {

            override fun onResponse(call: Call<CitiesModel>?, response: Response<CitiesModel>?)
            {
                if (response!!.isSuccessful() && response.code() == 200)
                {
                    try
                    {
                        viewState.postValue(response.body().data)
                    }
                    catch (e: Exception)
                    {
                    }

                } else {
                    viewState.postValue(null)
                }
            }

            override fun onFailure(call: Call<CitiesModel>?, t: Throwable?) {
                viewState.postValue(null)
            }
        })
    }
}