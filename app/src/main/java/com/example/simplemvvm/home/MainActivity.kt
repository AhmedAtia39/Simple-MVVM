package com.example.simplemvvm.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.simplemvvm.R
import com.example.simplemvvm.databinding.ActivityMainBinding
import com.example.simplemvvm.home.modelJson.CitiesData
import com.example.simplemvvm.home.modelJson.CitiesModel
import com.example.simplemvvm.login.LoginModel
import com.example.simplemvvm.login.LoginViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var adapterCities: AdapterCities
    lateinit var items: ArrayList<CitiesData>
    var city_position: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        items = ArrayList()
        adapterCities = AdapterCities(this,items,city_position)
        binding.listview.adapter = adapterCities

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.viewState.observe(this, Observer {

            binding.mProgress.visibility = View.GONE
            when(it)
            {
               null ->
               {
                 Toast.makeText(this,"حدث خطأ",Toast.LENGTH_LONG).show()
               }
                else ->
                {
                    for ( i in 0 .. it.size-1)
                         items.add(it.get(i))

                    adapterCities.notifyDataSetChanged()
                }
            }
        })

        binding.listview.setOnItemClickListener { parent, view, position, id ->
           adapterCities.set_position(position)
        }

        binding.mProgress.visibility = View.VISIBLE
        homeViewModel.getCities()
    }
}
