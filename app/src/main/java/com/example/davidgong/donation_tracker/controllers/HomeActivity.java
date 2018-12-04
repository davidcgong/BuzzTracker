package com.example.davidgong.donation_tracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Model;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Model model;

    private Button viewLocationsButton;
    private Button insertLocationsButton;
    private Button insertItemButton;
    private Button locationMapButton;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        model = Model.getInstance();
//        loadLocationData();

        accountType = getIntent().getStringExtra("ACCOUNT_TYPE");
        //if account type was determined to be location employee, show loc emp functionality
        if (accountType.equals("Location Employee")) {
            Toast.makeText(this, "Welcome, Location Employee!", Toast.LENGTH_SHORT).show();
        }


        viewLocationsButton = findViewById(R.id.locationViewButton);
        viewLocationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LocationViewActivity.class);
                startActivity(intent);
            }
        });

        insertLocationsButton = findViewById(R.id.insertLocationButton);
        insertLocationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeCheck()) {
                    Intent intent = new Intent(HomeActivity.this, InsertLocationActivity.class);
                    startActivity(intent);
                }
            }
        });

        insertItemButton = findViewById(R.id.insertItemButton);
        insertItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeCheck()) {
                    Intent intent = new Intent(HomeActivity.this, InsertItemActivity.class);
                    startActivity(intent);
                }
            }
        });

        locationMapButton = findViewById(R.id.locationMapButton);
        locationMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
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

    private boolean employeeCheck() {
        if (!accountType.equals("Location Employee") && !accountType.equals("Admin")) {
            Toast.makeText(this, "You're not a location employee or an admin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        int index = 0;
//        for (Location location : model.getLocations()) {
//            // Add markers for locations
//            Log.d("whatever", "Location " + index + ": " + location.getLocationName());
//            double currLatitude = location.getLatitude();
//            double currLongitude = location.getLongitude();
//            LatLng aLocation = new LatLng(currLatitude, currLongitude);
//            googleMap.addMarker(new MarkerOptions().position(aLocation).title(location.getLocationName()));
//            index++;
//        }


    }

//    private void loadLocationData() {
//        InputStream is = getResources().openRawResource(getResources().getIdentifier("location_data", "raw", getPackageName()));
//
//        List<Location> locations = new ArrayList<Location>();
//        String line;
//        boolean firstLine = true;
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            while((line = br.readLine()) != null) {
//                if (!firstLine) {
//                    String[] values = line.split(",");
//                    Location newLocation = new Location(values[1], values[8], Double.parseDouble(values[2]), Double.parseDouble(values[3]),
//                            values[4], values[5], values[6], values[7], values[9]);
//                    locations.add(newLocation);
//                } else {
//                    firstLine = false;
//                }
//            }
//        } catch (java.io.IOException ie) {
//
//        }
//
//        model.addAllLocations(locations);
//        Log.i("locations list size", Integer.toString(locations.size()));
//    }
}
