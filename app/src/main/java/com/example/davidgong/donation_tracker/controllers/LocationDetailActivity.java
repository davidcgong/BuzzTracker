package com.example.davidgong.donation_tracker.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.R;

public class LocationDetailActivity extends Activity {

    private Location thisLocation;

    private TextView nameText, typeText, coordinatesText, addressText, phoneNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        thisLocation = (Location) getIntent().getSerializableExtra("Location");

        nameText = (TextView) findViewById(R.id.locationName);
        typeText = (TextView) findViewById(R.id.locationType);
        coordinatesText = (TextView) findViewById(R.id.locationCoordinates);
        addressText = (TextView) findViewById(R.id.locationAddress);
        phoneNumberText = (TextView) findViewById(R.id.locationPhoneNumber);

        nameText.setText(thisLocation.getLocationName());
        typeText.setText(thisLocation.getLocationType() + "\n");
        coordinatesText.setText("GPS Coordinates: (" + thisLocation.getLatitude() + "," + thisLocation.getLongitude() + ")");
        addressText.setText(thisLocation.getStreetAddress() + "\n" + thisLocation.getCity() + "," + thisLocation.getState()
        + "," + thisLocation.getZip());
        phoneNumberText.setText(thisLocation.getPhoneNumber());
    }


}
