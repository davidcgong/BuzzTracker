package com.example.davidgong.donation_tracker.controllers

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Item
import com.example.davidgong.donation_tracker.model.Location
import com.example.davidgong.donation_tracker.model.Model

import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.util.Calendar

class InsertItemActivity : AppCompatActivity() {

    private var timestampButton: Button? = null
    private var addItemButton: Button? = null
    private var spinner: Spinner? = null
    private var catSpinner: Spinner? = null
    private var shortDesc: TextView? = null
    private var longDesc: TextView? = null
    private var value: TextView? = null

    private var model: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_item)

        model = Model.getInstance()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, model!!.locations)

        spinner = findViewById(R.id.locationSpinner)
        spinner!!.adapter = adapter

        catSpinner = findViewById(R.id.categorySpinner)

        catSpinner!!.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Item.ItemType.values())

        shortDesc = findViewById(R.id.shortDescText)
        longDesc = findViewById(R.id.longDescText)
        value = findViewById(R.id.valueText)

        timestampButton = findViewById(R.id.timestampButton)
        timestampButton!!.setOnClickListener {
            var dialogFragment: DialogFragment = DatePickerFragment()
            dialogFragment.show(supportFragmentManager, "Date Picker")

            dialogFragment = TimePickerFragment()
            dialogFragment.show(supportFragmentManager, "Time Picker")
        }

        addItemButton = findViewById(R.id.addItemButton)
        addItemButton!!.setOnClickListener {
            val timestamp = (findViewById<View>(R.id.timestampText) as TextView).text.toString()
            val time = timestamp.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]
            val date = timestamp.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]

            val newItem = Item(Integer.parseInt(date.split("/".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]), Integer.parseInt(date.split("/".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]), Integer.parseInt(date.split("/".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[2]), Integer.parseInt(time.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]), Integer.parseInt(time.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]),
                    spinner!!.selectedItem as Location, shortDesc!!.text.toString(), longDesc!!.text.toString(), value!!.text.toString(), catSpinner!!.selectedItem as Item.ItemType)
            (spinner!!.selectedItem as Location).addItem(newItem)

            writeModel()

            Toast.makeText(applicationContext, "Item Added", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@InsertItemActivity, HomeActivity::class.java)
            intent.putExtra("ACCOUNT_TYPE", "Location Employee")
            startActivity(intent)
        }
    }

    private fun writeModel() {
        var fout: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            fout = applicationContext.openFileOutput(model!!.locationFile, Context.MODE_PRIVATE)
            oos = ObjectOutputStream(fout)
            oos.writeObject(model)
            oos.close()

        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }

    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            return DatePickerDialog(activity!!, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        }

        override fun onDateSet(picker: DatePicker, year: Int, month: Int, day: Int) {
            val tv = activity!!.findViewById<TextView>(R.id.timestampText)

            tv.append(" $month/$day/$year")
        }
    }

    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            return TimePickerDialog(activity, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
        }

        override fun onTimeSet(picker: TimePicker, hour: Int, minute: Int) {
            val tv = activity!!.findViewById<TextView>(R.id.timestampText)

            tv.text = hour.toString() + ":" + minute
        }
    }
}
