package com.example.simplemvvm.login

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.simplemvvm.BR

class LoginModel : BaseObservable()
{
    @get:Bindable
    var phone:String?=null
      set(value) {
          field= value
          notifyPropertyChanged(BR.phone)
      }

    @get:Bindable
    var password:String?=null
        set(value) {
            field= value
            notifyPropertyChanged(BR.password)
        }

}