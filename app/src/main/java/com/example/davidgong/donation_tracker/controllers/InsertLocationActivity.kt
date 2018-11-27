package com.example.davidgong.donation_tracker.controllers

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Location
import com.example.davidgong.donation_tracker.model.Model

import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream


class InsertLocationActivity : AppCompatActivity() {

    private var locationName: EditText? = null
    private var locationTypeSpinner: Spinner? = null
    private var locationLatitude: EditText? = null
    private var locationLongitude: EditText? = null
    private var locationAddress: EditText? = null
    private var locationCity: EditText? = null
    private var locationState: EditText? = null
    private var locationZip: EditText? = null
    private var locationNumber: EditText? = null

    private var locationAddButton: Button? = null

    private val model = Model.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_location)

        locationName = findViewById(R.id.locationName)
        locationTypeSpinner = findViewById(R.id.locationTypeSpinner)
        locationLatitude = findViewById(R.id.locationLatitude)
        locationLongitude = findViewById(R.id.locationLongitude)
        locationAddress = findViewById(R.id.locationStreetAddress)
        locationCity = findViewById(R.id.locationCity)
        locationState = findViewById(R.id.locationState)
        locationZip = findViewById(R.id.locationZip)
        locationNumber = findViewById(R.id.locationPhoneNumber)

        locationAddButton = findViewById(R.id.locationAddButton)

        locationAddButton!!.setOnClickListener {
            val loc = Location(
                    locationName!!.text.toString(),
                    locationTypeSpinner!!.selectedItem.toString(),
                    java.lang.Double.parseDouble(locationLatitude!!.text.toString()),
                    java.lang.Double.parseDouble(locationLongitude!!.text.toString()),
                    locationAddress!!.text.toString(),
                    locationCity!!.text.toString(),
                    locationState!!.text.toString(),
                    locationZip!!.text.toString(),
                    locationNumber!!.text.toString())
            //                Location loc = new Location;
            model.addLocation(loc)
            writeModel()

            toastLocationAdded()
        }

    }

    private fun toastLocationAdded() {
        Toast.makeText(this, "Location has been added.", Toast.LENGTH_SHORT).show()
    }

    private fun writeModel() {
        var fout: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            fout = applicationContext.openFileOutput(model.locationFile, Context.MODE_PRIVATE)
            oos = ObjectOutputStream(fout)
            oos.writeObject(model)
            oos.close()

        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }
}