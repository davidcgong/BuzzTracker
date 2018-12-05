package com.example.davidgong.donation_tracker.controllers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Location
import com.example.davidgong.donation_tracker.model.Model

class LocationViewActivity : Activity() {

    private var model: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_view)

        model = Model.getInstance()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, model!!.locations)

        val locationList = findViewById<ListView>(R.id._locations)
        locationList.adapter = adapter

        locationList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@LocationViewActivity, LocationDetailActivity::class.java)

            val selectedLocation = model!!.locations[position]
            intent.putExtra("Location", selectedLocation)

            startActivity(intent)
        }
    }

}
