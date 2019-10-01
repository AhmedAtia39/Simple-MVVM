package com.example.simplemvvm.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.simplemvvm.R
import com.example.simplemvvm.databinding.ActivityLoginBinding
import com.example.simplemvvm.home.MainActivity

class LoginActivity : AppCompatActivity() {

    /*
     phone :   0554554681
     pass :   12345678
    */

    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var loginModel: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = LoginViewModel()
        lifecycle.addObserver(loginViewModel)

        loginModel= LoginModel()
        binding.model=loginModel

        loginViewModel.viewState.observe(this, Observer {

            binding.mProgress.visibility = View.GONE
            when(it?.state)
            {
                1 ->
                 goHome()
                else ->
                    Toast.makeText(this,it?.msg , Toast.LENGTH_LONG).show()
            }
        })

        binding.login.setOnClickListener {

            var phone = binding.model!!.phone
            var password = binding.model!!.password

            if(phone==null)
                Toast.makeText(this,getString(R.string.write_phone), Toast.LENGTH_LONG).show()
            else if(password==null)
                Toast.makeText(this,getString(R.string.write_pass), Toast.LENGTH_LONG).show()
            else
            {
                binding.mProgress.visibility = View.VISIBLE
                loginViewModel.login(phone,password)
            }

        }

    }

    private fun goHome()
    {
        var intent_login =  Intent(this,MainActivity::class.java)
        intent_login!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent_login!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent_login)
        finishAffinity()
    }
}
