package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by berso on 4/28/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private List<Earthquake> mEarthquake;
    private Context mContext;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
        mEarthquake = earthquakes;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final EarthquakeViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);

            viewHolder = new EarthquakeViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EarthquakeViewHolder) convertView.getTag();
        }

        final Earthquake earthquake = mEarthquake.get(position);

        double magnitude = earthquake.getMagnitude();
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(magnitude);

        viewHolder.magnitudeTextView.setText("" + magnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) viewHolder.magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String place = earthquake.getPlace();
        String offset = this.getOffset(place);
        String location = this.getLocation(place);
        viewHolder.offsetTextView.setText(offset);
        viewHolder.locationTextView.setText(location);

        Long mDate = earthquake.getTimeInMilliseconds();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String sDate = dateFormatter.format(mDate);
        viewHolder.dateTextView.setText(sDate);


        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:ss a");
        String sTime = timeFormatter.format(mDate);
        viewHolder.timeTextView.setText(sTime);




        viewHolder.rootLayOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = earthquake.getUrl();
                Log.v("click",url);
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }


    static class EarthquakeViewHolder {

        private TextView magnitudeTextView;
        private TextView offsetTextView;
        private TextView locationTextView;
        private TextView dateTextView;
        private TextView timeTextView;
        private LinearLayout rootLayOut;

        public EarthquakeViewHolder(@NonNull View view) {
            this.magnitudeTextView = (TextView) view.findViewById(R.id.magnitude);
            this.offsetTextView = (TextView) view.findViewById(R.id.location_offset);
            this.locationTextView = (TextView) view.findViewById(R.id.primary_location);
            this.dateTextView = (TextView) view.findViewById(R.id.date);
            this.timeTextView = (TextView) view.findViewById(R.id.time);
            this.rootLayOut = (LinearLayout) view.findViewById(R.id.rootLayout);
        }

    }

    private String getOffset(String place) {
        String offset = "Near the ";
        if (place.contains("of")) {
            int indexOf = place.indexOf(" of ") + 3;
            offset = place.substring(0, indexOf);

        }
        return offset;
    }

    private String getLocation(String place) {
        String location = place;
        if (place.contains("of")) {
            int indexOf = place.indexOf(" of ");
            location = place.substring(indexOf + 3, place.length());
        }
        return location;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
