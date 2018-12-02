package com.example.davidgong.donation_tracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Account;
import com.example.davidgong.donation_tracker.model.Model;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RegistrationActivity extends AppCompatActivity {
    private AutoCompleteTextView username;
    private EditText password;
    private EditText confirmPassword;
    private Spinner locationSpinner;
    private final Model model = Model.getInstance();
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    //for some reason database references in firebase cause an error with the arrayadapter in the model
    // for the sake of time I'm not following a bit of the architectural style and putting the database
    // reference here.

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        //grabbing veiws
        Button registerBtn = findViewById(R.id.btn_register);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        confirmPassword = findViewById(R.id.txt_confirmPassword);
        locationSpinner = findViewById(R.id.locationSpinner);
        radioGroup = findViewById(R.id.radioUserType);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, model.getLocations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get selected radio buttom from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                //setup all variables used
                radioButton = findViewById(selectedId);
                String usernametxt = username.getText().toString();
                String passwordtxt = password.getText().toString();
                String usertypetxt = radioButton.getText().toString();
                String confirmPasswordtxt = confirmPassword.getText().toString();
                String location = locationSpinner.getSelectedItem().toString();
                boolean valid = true;
                //remove any previous errors

                username.setError(null);
                password.setError(null);
                confirmPassword.setError(null);
                //get errors with username
                if (TextUtils.isEmpty(usernametxt)) {
                    username.setError(getString(R.string.error_field_required));
                    valid = false;
                } else if (model.containsUsername(usernametxt)) {
                    username.setError(getString(R.string.error_username_taken));
                    valid = false;
                } else if (!model.validUsername(usernametxt)) {
                    username.setError(getString(R.string.error_invalid_username));
                    valid = false;
                }
                //get errors with password
                if (TextUtils.isEmpty(passwordtxt)) {
                    password.setError(getString(R.string.error_field_required));
                    valid = false;
                } else if (!model.validPassword(passwordtxt)) {
                    password.setError(getString(R.string.error_invalid_password));
                    valid = false;
                }
                //get errors with confirm password
                if (TextUtils.isEmpty(confirmPasswordtxt)) {
                    confirmPassword.setError(getString(R.string.error_field_required));
                    valid = false;
                } else if (!passwordtxt.equals(confirmPasswordtxt)) {
                    confirmPassword.setError(getString(R.string.error_password_mismatch));
                    valid = false;
                }
                //add new user and move to login activity if no errors occurred
                if (valid) {
                    if (usertypetxt == "Location Employee") {
                        model.addAccount(usernametxt, passwordtxt, usertypetxt, location);
                        databaseReference.child(usernametxt).setValue(new Account(usernametxt, passwordtxt, usertypetxt, location));
                    } else {
                        model.addAccount(usernametxt, passwordtxt, usertypetxt);
                        databaseReference.child(usernametxt).setValue(new Account(usernametxt, passwordtxt, usertypetxt));
                    }

                    writeModel();

                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void writeModel() {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = getApplicationContext().openFileOutput(model.locationFile, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(model);
            oos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
