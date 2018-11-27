package com.example.davidgong.donation_tracker.controllers

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Item

class ItemDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val item = intent.getSerializableExtra("Item") as Item

        val info = findViewById<TextView>(R.id.itemDetails)
        info.text = "Added on " + item.month + "/" + item.day + "/" + item.year + " at " + item.hour + ":" + item.minute + ".\n" +
                "Location: " + item.location.locationName + "\n" +
                "Description: " + item.longDesc + "\n" +
                "Value: " + item.value + "\n" +
                "Category: " + item.itemType.name


    }

}
