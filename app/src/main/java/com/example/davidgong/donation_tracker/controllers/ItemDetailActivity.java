package com.example.davidgong.donation_tracker.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Item;

/**
 * activity for android
 */

public class ItemDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Item item = (Item) getIntent().getSerializableExtra("Item");

        TextView info = findViewById(R.id.itemDetails);
        info.setText(
                "Added on " + item.getMonth() + "/" + item.getDay() + "/" + item.getYear() + " at " + item.getHour() + ":" + item.getMinute() + ".\n" +
                        "Location: " + item.getLocation().getLocationName() + "\n" +
                        "Description: " + item.getLongDesc() + "\n" +
                        "Value: " + item.getValue() + "\n" +
                        "Category: " + item.getItemType().name()
        );


    }

}
