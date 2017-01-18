package com.example.cpicha.truetravellite;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip{
    String Name;
    String Location;
    String Travel;

    public Trip(String name, String location, String travel) {
        Name = name;
        Location = location;
        Travel = travel;
    }

    public String getTravel() {
        return Travel;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }

}