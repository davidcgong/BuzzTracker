package com.example.davidgong.donation_tracker.controllers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Account
import com.example.davidgong.donation_tracker.model.Model
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

class RegistrationActivity : AppCompatActivity() {
    private var username: AutoCompleteTextView? = null
    private var password: EditText? = null
    private var confirmPassword: EditText? = null
    private var locationSpinner: Spinner? = null
    private val model = Model.getInstance()
    private var radioGroup: RadioGroup? = null
    private var radioButton: RadioButton? = null

    //for some reason database references in firebase cause an error with the arrayadapter in the model
    // for the sake of time I'm not following a bit of the architectural style and putting the database
    // reference here.

    private var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        FirebaseApp.initializeApp(this)
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        //grabbing veiws
        val registerBtn = findViewById<Button>(R.id.btn_register)
        username = findViewById(R.id.txt_username)
        password = findViewById(R.id.txt_password)
        confirmPassword = findViewById(R.id.txt_confirmPassword)
        locationSpinner = findViewById(R.id.locationSpinner)
        radioGroup = findViewById(R.id.radioUserType)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, model.locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner!!.adapter = adapter

        registerBtn.setOnClickListener {
            //get selected radio buttom from radioGroup
            val selectedId = radioGroup!!.checkedRadioButtonId
            //setup all variables used
            radioButton = findViewById(selectedId)
            val usernametxt = username!!.text.toString()
            val passwordtxt = password!!.text.toString()
            val usertypetxt = radioButton!!.text.toString()
            val confirmPasswordtxt = confirmPassword!!.text.toString()
            val location = locationSpinner!!.selectedItem.toString()
            var valid = true
            //remove any previous errors

            username!!.error = null
            password!!.error = null
            confirmPassword!!.error = null
            //get errors with username
            if (TextUtils.isEmpty(usernametxt)) {
                username!!.error = getString(R.string.error_field_required)
                valid = false
            } else if (model.containsUsername(usernametxt)) {
                username!!.error = getString(R.string.error_username_taken)
                valid = false
            } else if (!model.validUsername(usernametxt)) {
                username!!.error = getString(R.string.error_invalid_username)
                valid = false
            }
            //get errors with password
            if (TextUtils.isEmpty(passwordtxt)) {
                password!!.error = getString(R.string.error_field_required)
                valid = false
            } else if (!model.validPassword(passwordtxt)) {
                password!!.error = getString(R.string.error_invalid_password)
                valid = false
            }
            //get errors with confirm password
            if (TextUtils.isEmpty(confirmPasswordtxt)) {
                confirmPassword!!.error = getString(R.string.error_field_required)
                valid = false
            } else if (passwordtxt != confirmPasswordtxt) {
                confirmPassword!!.error = getString(R.string.error_password_mismatch)
                valid = false
            }
            //add new user and move to login activity if no errors occurred
            if (valid) {
                if (usertypetxt === "Location Employee") {
                    model.addAccount(usernametxt, passwordtxt, usertypetxt, location)
                    databaseReference!!.child(usernametxt).setValue(Account(usernametxt, passwordtxt, usertypetxt, location))
                } else {
                    model.addAccount(usernametxt, passwordtxt, usertypetxt)
                    databaseReference!!.child(usernametxt).setValue(Account(usernametxt, passwordtxt, usertypetxt))
                }

                writeModel()

                val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
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
