package com.example.simplemvvm.login

class ModelLogin(state:Int,msg:String)
{
    companion object
    {
        var loading = 0
        var successLogin = 1
        var ErrorLogin = 2
        var FailureLogin = 3
    }

    var state = -1
    var msg = ""

    init {
        this.state = state
        this.msg = msg
    }
}