package com.example.cpicha.truetravellite;

import java.util.ArrayList;

/**
 * autocreate
 */

public class TripList {
private ArrayList<Trip> alltrips;

    public TripList(ArrayList<Trip> data){
        this.alltrips =data;
    }

    public ArrayList<Trip> getAlltrips(){
        return this.alltrips;
    }


    public String getTripinfo(int index){

       String info= alltrips.get(index).getName().toString()+" "+alltrips.get(index).getLocation().toString()+" "+alltrips.get(index).getTravel().toString();
        return info;
    }

    public void addd(Trip trip){
        alltrips.add(trip);
    }
}


