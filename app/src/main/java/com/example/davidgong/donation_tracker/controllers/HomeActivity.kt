package com.example.davidgong.donation_tracker.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Model
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class HomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private var model: Model? = null

    private var viewLocationsButton: Button? = null
    private var insertLocationsButton: Button? = null
    private var insertItemButton: Button? = null
    private var locationMapButton: Button? = null
    private var accountType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        model = Model.getInstance()
        //        loadLocationData();

        accountType = intent.getStringExtra("ACCOUNT_TYPE")
        //if account type was determined to be location employee, show loc emp functionality
        if (accountType == "Location Employee") {
            Toast.makeText(this, "Welcome, Location Employee!", Toast.LENGTH_SHORT).show()
        } else if (accountType == "Admin") {
            Toast.makeText(this, "Welcome, Administrator!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Welcome, User!", Toast.LENGTH_SHORT).show()
        }


        viewLocationsButton = findViewById(R.id.locationViewButton)
        viewLocationsButton!!.setOnClickListener {
            val intent = Intent(this@HomeActivity, LocationViewActivity::class.java)
            startActivity(intent)
        }

        insertLocationsButton = findViewById(R.id.insertLocationButton)
        insertLocationsButton!!.setOnClickListener {
            if (employeeCheck()) {
                val intent = Intent(this@HomeActivity, InsertLocationActivity::class.java)
                startActivity(intent)
            }
        }

        insertItemButton = findViewById(R.id.insertItemButton)
        insertItemButton!!.setOnClickListener {
            if (employeeCheck()) {
                val intent = Intent(this@HomeActivity, InsertItemActivity::class.java)
                startActivity(intent)
            }
        }

        locationMapButton = findViewById(R.id.locationMapButton)
        locationMapButton!!.setOnClickListener {
            val intent = Intent(this@HomeActivity, MapsActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        title = "Home"
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out_app -> {
                //add in more log out functionality at some point here and maybe make modular
                Toast.makeText(this, "Logging you out...", Toast.LENGTH_SHORT).show()
                val returnToMain = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(returnToMain)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun employeeCheck(): Boolean {
        if (accountType != "Location Employee" && accountType != "Admin") {
            Toast.makeText(this, "You're not a location employee or an admin", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
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
