package com.example.simplemvvm.login

import android.arch.lifecycle.*
import com.example.simplemvvm.remote.ApiService
import com.example.simplemvvm.remote.RetrofitClass
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginViewModel : LifecycleObserver
{
    var viewState:MutableLiveData<ModelLogin> = MutableLiveData()
    lateinit var service: ApiService
    lateinit var jsonObject: JSONObject

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun action_onCreate()
    {
        viewState.postValue(ModelLogin(ModelLogin.loading, "onCreate Login Activity"))
        service = RetrofitClass.instance.mService()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
     fun action_onStop()
    {
        viewState.postValue(ModelLogin(ModelLogin.loading, "onStop Login Activity"))
    }

    fun login(phone: String, password: String)
    {
       service.login(phone, password).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful)
                {
                    try
                    {
                        jsonObject = JSONObject(response.body().string())
                        val token = jsonObject.getString("token")
                        viewState.postValue(ModelLogin(ModelLogin.successLogin, token))
                    } catch (e: Exception) {
                    }
                }
                else
                {
                    viewState.postValue(ModelLogin(ModelLogin.ErrorLogin, "تأكد من بياناتك"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                viewState.postValue(ModelLogin(ModelLogin.FailureLogin,"حدث خطأ في الإتصال"))
            }
        })
    }
}