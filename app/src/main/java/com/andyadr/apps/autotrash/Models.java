package com.andyadr.apps.autotrash;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Models implements Parcelable {


    @SerializedName("id")
    private String id;


    @SerializedName("status_sampah")
    private String status_sampah;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getStatus_sampah() {
        return status_sampah;
    }

    public void setStatus_sampah(String status_sampah) {
        this.status_sampah = status_sampah;
    }

    public Models() {
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(status_sampah);
    }

    protected Models(Parcel in) {
        id = in.readString();
        status_sampah = in.readString();
    }

    public static final Creator<Models> CREATOR = new Creator<Models>() {
        @Override
        public Models createFromParcel(Parcel in) {
            return new Models(in);
        }

        @Override
        public Models[] newArray(int size) {
            return new Models[size];
        }
    };
}
