package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berso on 4/28/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private List<Earthquake> mEarthquake;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
        mEarthquake = earthquakes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EarthquakeViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);

            viewHolder = new EarthquakeViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EarthquakeViewHolder) convertView.getTag();
        }

        Earthquake earthquake = mEarthquake.get(position);

        viewHolder.magnitudeTextView.setText(earthquake.getMagnitude());
        viewHolder.cityTextView.setText(earthquake.getPlace());
        viewHolder.dateTextView.setText(earthquake.getDate());


        return convertView;
    }

    static class EarthquakeViewHolder {

        private TextView magnitudeTextView;
        private TextView cityTextView;
        private TextView dateTextView;

        public EarthquakeViewHolder(@NonNull View view) {
            this.magnitudeTextView = (TextView)view.findViewById(R.id.magnitude_text_view);
            this.cityTextView = (TextView)view.findViewById(R.id.city_text_view);
            this.dateTextView = (TextView)view.findViewById(R.id.date_text_view);
        }
    }
}
