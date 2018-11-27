package com.example.davidgong.donation_tracker.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Location
import com.example.davidgong.donation_tracker.model.Model

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var loginButton: Button? = null
    private var registrationButton: Button? = null
    private val locationsButton: Button? = null

    private var model: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = Model.getInstance()

        //hardcoding my name1 in because it's annoying to register all the time
        model!!.addAccount("abhishek", "abhishek", "Location Employee", "asdf")

        loginButton = findViewById(R.id.logIn_button)
        registrationButton = findViewById(R.id.registration_button)
        //        locationsButton = (Button) findViewById(R.id.locations_button);

        val clearSavedModel = false

        if (clearSavedModel && File(filesDir.absolutePath + "/" + model!!.locationFile).exists()) {
            File(filesDir.absolutePath + "/" + model!!.locationFile).delete()
        }


        if (File(filesDir.absolutePath + "/" + model!!.locationFile).exists()) {
            loadModel()
        } else {
            // load default location data
            loadLocationData()
        }

        loginButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        registrationButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        //        locationsButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Intent intent = new Intent(MainActivity.this, LocationDetailsActivity.class);
        //                startActivity(intent);
        //            }
        //        });
    }

    private fun loadModel() {
        var fin: FileInputStream? = null
        var ois: ObjectInputStream? = null

        try {
            fin = applicationContext.openFileInput(model!!.locationFile)
            ois = ObjectInputStream(fin)
            val savedModel = ois.readObject() as Model
            ois.close()

            model!!.loadModel(savedModel)

        } catch (ioe: IOException) {
            ioe.printStackTrace()
        } catch (cnfe: ClassNotFoundException) {
            cnfe.printStackTrace()
        }

    }

    private fun loadLocationData() {
        val `is` = resources.openRawResource(resources.getIdentifier("location_data", "raw", packageName))

        val locations = ArrayList<Location>()
        var line: String
        var firstLine = true

        try {
            val br = BufferedReader(InputStreamReader(`is`))
            line = br.readLine()
            while (line != null) {
                if (!firstLine) {
                    val values = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val newLocation = Location(values[1], values[8], java.lang.Double.parseDouble(values[2]), java.lang.Double.parseDouble(values[3]),
                            values[4], values[5], values[6], values[7], values[9])
                    locations.add(newLocation)
                } else {
                    firstLine = false
                }
            }
        } catch (ie: java.io.IOException) {

        }

        model!!.addAllLocations(locations)
        Log.i("locations list size", Integer.toString(locations.size))
    }
}
