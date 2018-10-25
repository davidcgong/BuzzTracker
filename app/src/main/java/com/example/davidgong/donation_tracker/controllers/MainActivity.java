package com.example.davidgong.donation_tracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.davidgong.donation_tracker.model.CSVFile;
import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registrationButton;
    private Button locationsButton;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = Model.getInstance();

        loginButton = (Button) findViewById(R.id.logIn_button);
        registrationButton = (Button) findViewById(R.id.registration_button);
//        locationsButton = (Button) findViewById(R.id.locations_button);

        loadLocationData(); // load default location data

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

//        locationsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, LocationDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
    }

        private void loadLocationData() {
        InputStream is = getResources().openRawResource(getResources().getIdentifier("location_data", "raw", getPackageName()));

        List<Location> locations = new ArrayList<Location>();
        String line;
        boolean firstLine = true;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while((line = br.readLine()) != null) {
                if (!firstLine) {
                    String[] values = line.split(",");
                    Location newLocation = new Location(values[1], values[8], Double.parseDouble(values[2]), Double.parseDouble(values[3]),
                            values[4], values[5], values[6], values[7], values[9]);
                    locations.add(newLocation);
                } else {
                    firstLine = false;
                }
            }
        } catch (java.io.IOException ie) {

        }

        model.addLocations(locations);
        Log.i("locations list size", Integer.toString(locations.size()));
    }
}
