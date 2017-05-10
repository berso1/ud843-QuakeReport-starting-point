package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


/**
 * Created by berso on 5/9/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context,String Url) {
        super(context);
        mUrl = Url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i("onStartLoading","TEST");
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i("loadInBackground","TEST");
        if (mUrl == null) {
            return null;
        }

        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }


}
