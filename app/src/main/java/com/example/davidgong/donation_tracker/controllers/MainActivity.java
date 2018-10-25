package com.example.davidgong.donation_tracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.davidgong.donation_tracker.model.CSVFile;
import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Model;

import java.io.InputStream;
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

        loginButton = (Button) findViewById(R.id.logIn_button);
        registrationButton = (Button) findViewById(R.id.registration_button);
        locationsButton = (Button) findViewById(R.id.locations_button);

        model = Model.getInstance();
        InputStream inputStream = getResources().openRawResource(R.raw.location_data);
        CSVFile csvFile = new CSVFile(inputStream);
        List locations = csvFile.read();
        model.addLocations(locations);

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
}
