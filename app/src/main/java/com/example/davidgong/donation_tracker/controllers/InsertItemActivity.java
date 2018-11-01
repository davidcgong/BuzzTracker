package com.example.davidgong.donation_tracker.controllers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Item;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

public class InsertItemActivity extends AppCompatActivity {

    private Button timestampButton, addItemButton;
    private Spinner spinner, catSpinner;
    private TextView shortDesc, longDesc, value;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

        model = Model.getInstance();

        ArrayAdapter adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, model.getLocations());

        spinner = (Spinner) findViewById(R.id.locationSpinner);
        spinner.setAdapter(adapter);

        catSpinner = (Spinner) findViewById(R.id.categorySpinner);

        catSpinner.setAdapter(new ArrayAdapter<Item.ItemType>(this, android.R.layout.simple_spinner_item, Item.ItemType.values()));

        shortDesc = findViewById(R.id.shortDescText);
        longDesc = findViewById(R.id.longDescText);
        value = findViewById(R.id.valueText);

        timestampButton = (Button) findViewById(R.id.timestampButton);
        timestampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");

                dialogFragment = new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        addItemButton = (Button) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timestamp = ((TextView)findViewById(R.id.timestampText)).getText().toString();
                String time = timestamp.split(" ")[0];
                String date = timestamp.split(" ")[1];

                Item newItem = new Item(Integer.parseInt(date.split("/")[1]), Integer.parseInt(date.split("/")[0]), Integer.parseInt(date.split("/")[2]), Integer.parseInt(time.split(":")[0]), Integer.parseInt(time.split(":")[1]),
                        (Location)spinner.getSelectedItem(), shortDesc.getText().toString(), longDesc.getText().toString(), value.getText().toString(), (Item.ItemType)catSpinner.getSelectedItem());
                ((Location) spinner.getSelectedItem()).addItem(newItem);

                writeModel();

                Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InsertItemActivity.this, HomeActivity.class);
                intent.putExtra("ACCOUNT_TYPE", "Location Employee");
                startActivity(intent);
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

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            return new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }

        public void onDateSet(DatePicker picker, int year, int month, int day) {
            TextView tv = getActivity().findViewById(R.id.timestampText);

            tv.append(" " + month + "/" + day + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            return new TimePickerDialog(getActivity(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        }

        public void onTimeSet(TimePicker picker, int hour, int minute) {
            TextView tv = getActivity().findViewById(R.id.timestampText);

            tv.setText(hour + ":" + minute);
        }
    }
}
