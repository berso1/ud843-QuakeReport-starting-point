package com.example.android.quakereport;

/**
 * Created by berso on 4/28/17.
 */

public class Earthquake {
    private String mMagnitude;
    private String mPlace;
    private String mDate;

    public Earthquake(String magnitude, String place, String eQdate){
        mMagnitude = magnitude;
        mPlace     = place;
        mDate      = eQdate;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDate() {
        return mDate;
    }


}
