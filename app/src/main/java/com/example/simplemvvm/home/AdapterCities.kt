package com.example.simplemvvm.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.simplemvvm.R
import com.example.simplemvvm.home.modelJson.CitiesData
import java.util.ArrayList

class AdapterCities(internal var context: Context, items: ArrayList<CitiesData>, last_pos: Int) : BaseAdapter()
{
    var selected_position = -1
    internal var items = ArrayList<CitiesData>()
    lateinit var mView: View

    init {
        this.items = items
        this.selected_position = last_pos
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position].getName()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        mView = LayoutInflater.from(context).inflate(R.layout.item_listview, null)
        val textView = mView.findViewById<TextView>(R.id.bottom_txt)

        textView.setText(items[position].getName())

        if (selected_position == position)
            textView.setTextColor(Color.BLACK)
        else
            textView.setTextColor(Color.parseColor("#9B9B9B"))

        return mView
    }

    fun set_position(pos: Int) {
        selected_position = pos
        notifyDataSetChanged()
    }
}

