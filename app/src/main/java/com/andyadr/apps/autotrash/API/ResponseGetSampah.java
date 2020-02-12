package com.andyadr.apps.autotrash.API;

import com.andyadr.apps.autotrash.Models;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseGetSampah {
    @SerializedName("data")
    ArrayList<Models> mSampah;

    public ArrayList<Models> getmSampah() {
   return mSampah;
    }
    public void setKontak(ArrayList<Models> user) {
     mSampah = user;
     }

}
