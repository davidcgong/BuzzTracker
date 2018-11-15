package com.example.davidgong.donation_tracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;
import com.example.davidgong.donation_tracker.R;

public class LocationActivity extends Activity {

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        model = Model.getInstance();

        ArrayAdapter adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, model.getLocations());

        ListView locationList = (ListView) findViewById(R.id._locations);
        locationList.setAdapter(adapter);

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocationActivity.this, LocationDetailActivity.class);

                Location selectedLocation = model.getLocations().get(position);
                intent.putExtra("Location", selectedLocation);

                startActivity(intent);
            }
        });
    }

}
