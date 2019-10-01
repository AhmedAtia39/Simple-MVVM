
package com.example.simplemvvm.home.modelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CitiesModel {

    @SerializedName("data")
    @Expose
    private ArrayList<CitiesData> data = null;

    public ArrayList<CitiesData> getData() {
        return data;
    }

    public void setData(ArrayList<CitiesData> data) {
        this.data = data;
    }

}
