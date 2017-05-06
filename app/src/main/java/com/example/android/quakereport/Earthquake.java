package com.example.android.quakereport;


/**
 * Created by berso on 4/28/17.
 */

public class Earthquake {
    private double mMagnitude;
    private String mPlace;
    private long mDate;
    private String mUrl;

    public Earthquake(double magnitude, String place, long eQdate, String url){
        mMagnitude = magnitude;
        mPlace     = place;
        mDate      = eQdate;
        mUrl       = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public Long getTimeInMilliseconds(){
        return mDate;
    }

    public String getUrl() { return  mUrl; }

}
