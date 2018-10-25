package com.example.davidgong.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Model model;

    private Button viewLocationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        model = Model.getInstance();
        loadLocationData();

        String accountType = getIntent().getStringExtra("ACCOUNT_TYPE");
        //if account type was determined to be location employee, show loc emp functionality
        if (accountType.equals("Location Employee")) {
            Toast.makeText(this, "Welcome, Location Employee!", Toast.LENGTH_SHORT).show();
        }

        viewLocationsButton = (Button) findViewById(R.id.locationButton);
        viewLocationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Home");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_app:
                //add in more log out functionality at some point here and maybe make modular
                Toast.makeText(this, "Logging you out...", Toast.LENGTH_SHORT).show();
                Intent returnToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(returnToMain);
        }
        return super.onOptionsItemSelected(item);
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
