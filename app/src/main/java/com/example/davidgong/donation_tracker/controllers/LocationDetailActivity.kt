package com.example.davidgong.donation_tracker.controllers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.example.davidgong.donation_tracker.R
import com.example.davidgong.donation_tracker.model.Item
import com.example.davidgong.donation_tracker.model.Location
import com.example.davidgong.donation_tracker.model.Model

import java.util.ArrayList

class LocationDetailActivity : Activity() {

    private var thisLocation: Location? = null

    private var nameText: TextView? = null
    private var typeText: TextView? = null
    private var coordinatesText: TextView? = null
    private var addressText: TextView? = null
    private var phoneNumberText: TextView? = null

    private var searchView: SearchView? = null

    private var catSpinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        val model = Model.getInstance()

        thisLocation = intent.getSerializableExtra("Location") as Location

        nameText = findViewById(R.id.locationName)
        typeText = findViewById(R.id.locationType)
        coordinatesText = findViewById(R.id.locationCoordinates)
        addressText = findViewById(R.id.locationAddress)
        phoneNumberText = findViewById(R.id.locationPhoneNumber)

        searchView = findViewById(R.id.Item_Search)

        catSpinner = findViewById(R.id.categorySpinner)
        catSpinner!!.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Item.ItemType.values())

        nameText!!.text = thisLocation!!.locationName
        typeText!!.text = thisLocation!!.locationType + "\n"
        coordinatesText!!.text = "GPS Coordinates: (" + thisLocation!!.latitude + "," + thisLocation!!.longitude + ")"
        addressText!!.text = (thisLocation!!.streetAddress + "\n" + thisLocation!!.city + "," + thisLocation!!.state
                + "," + thisLocation!!.zip)
        phoneNumberText!!.text = thisLocation!!.phoneNumber

        searchView!!.queryHint = "Search Item"

        addItems(loadItemList("", Item.ItemType.NONE))

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                addItems(loadItemList(searchView!!.query.toString(), Item.ItemType.NONE))
                return true
            }
        })

        catSpinner!!.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                addItems(loadItemList(searchView!!.query.toString(), catSpinner!!.selectedItem as Item.ItemType))
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        })

    }


    private fun addItems(foundItems: ArrayList<Item>) {
        val adapter = ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1)

        adapter.addAll(foundItems)

        if (foundItems.isEmpty()) {
            Toast.makeText(this, "There are no items matching your search.", Toast.LENGTH_LONG).show()
        }

        val itemList = findViewById<ListView>(R.id._items)
        itemList.adapter = adapter

        itemList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@LocationDetailActivity, ItemDetailActivity::class.java)

            val selectedItem = thisLocation!!.items[position]
            intent.putExtra("Item", selectedItem)

            startActivity(intent)
        }
    }

    private fun loadItemList(search: String, itemType: Item.ItemType): ArrayList<Item> {
        val foundItems = ArrayList<Item>()

        if (search !== "" && itemType != Item.ItemType.NONE) {
            for (i in thisLocation!!.items) {
                if (i.shortDesc.contains(search) && i.itemType == itemType) {
                    foundItems.add(i)
                }
            }
        } else if (search !== "") {
            for (i in thisLocation!!.items) {
                if (i.shortDesc.contains(search)) {
                    foundItems.add(i)
                }
            }
        } else if (itemType != Item.ItemType.NONE) {
            for (i in thisLocation!!.items) {
                if (i.itemType == itemType) {
                    foundItems.add(i)
                }
            }
        } else {
            foundItems.addAll(thisLocation!!.items)
        }


        return foundItems
    }


}
