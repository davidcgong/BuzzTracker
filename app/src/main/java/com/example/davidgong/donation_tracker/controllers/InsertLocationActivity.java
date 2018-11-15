package com.example.davidgong.donation_tracker.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * activity for android
 */

public class InsertLocationActivity extends AppCompatActivity {

    private EditText locationName;
    private Spinner locationTypeSpinner;
    private EditText locationLatitude;
    private EditText locationLongitude;
    private EditText locationAddress;
    private EditText locationCity;
    private EditText locationState;
    private EditText locationZip;
    private EditText locationNumber;

    private Button locationAddButton;

    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_location);

        locationName = findViewById(R.id.locationName);
        locationTypeSpinner = findViewById(R.id.locationTypeSpinner);
        locationLatitude = findViewById(R.id.locationLatitude);
        locationLongitude = findViewById(R.id.locationLongitude);
        locationAddress = findViewById(R.id.locationStreetAddress);
        locationCity = findViewById(R.id.locationCity);
        locationState = findViewById(R.id.locationState);
        locationZip = findViewById(R.id.locationZip);
        locationNumber = findViewById(R.id.locationPhoneNumber);

        locationAddButton = findViewById(R.id.locationAddButton);

        locationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location loc = new Location(
                        locationName.getText().toString(),
                        locationTypeSpinner.getSelectedItem().toString(),
                        Double.parseDouble(locationLatitude.getText().toString()),
                        Double.parseDouble(locationLongitude.getText().toString()),
                        locationAddress.getText().toString(),
                        locationCity.getText().toString(),
                        locationState.getText().toString(),
                        locationZip.getText().toString(),
                        locationNumber.getText().toString());
//                Location loc = new Location;
                model.addLocation(loc);
                writeModel();

                toastLocationAdded();
            }

        });

    }

    private void toastLocationAdded() {
        Toast.makeText(this, "Location has been added.", Toast.LENGTH_SHORT).show();
    }

    private void writeModel() {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = getApplicationContext().openFileOutput(model.locationFile, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(model);
            oos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}