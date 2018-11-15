package com.example.davidgong.donation_tracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidgong.donation_tracker.model.Item;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Model;

public class LocationDetailActivity extends Activity {

    private Location thisLocation;

    private TextView nameText, typeText, coordinatesText, addressText, phoneNumberText;

    private SearchView searchView;

    private Spinner catSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        Model model = Model.getInstance();

        thisLocation = (Location) getIntent().getSerializableExtra("Location");

        nameText = findViewById(R.id.locationName);
        typeText = findViewById(R.id.locationType);
        coordinatesText = findViewById(R.id.locationCoordinates);
        addressText = findViewById(R.id.locationAddress);
        phoneNumberText = findViewById(R.id.locationPhoneNumber);

        searchView = findViewById(R.id.Item_Search);

        catSpinner = findViewById(R.id.categorySpinner);
        catSpinner.setAdapter(new ArrayAdapter<Item.ItemType>(this, android.R.layout.simple_spinner_item, Item.ItemType.values()));

        nameText.setText(thisLocation.getLocationName());
        typeText.setText(thisLocation.getLocationType() + "\n");
        coordinatesText.setText("GPS Coordinates: (" + thisLocation.getLatitude() + "," + thisLocation.getLongitude() + ")");
        addressText.setText(thisLocation.getStreetAddress() + "\n" + thisLocation.getCity() + "," + thisLocation.getState()
        + "," + thisLocation.getZip());
        phoneNumberText.setText(thisLocation.getPhoneNumber());

        searchView.setQueryHint("Search Item");

        loadItemList("", Item.ItemType.NONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                loadItemList(searchView.getQuery().toString(), Item.ItemType.NONE);
                return true;
            }
        });

        catSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadItemList(searchView.getQuery().toString(), (Item.ItemType) catSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }


    private void loadItemList(String search, Item.ItemType itemType){
        ArrayAdapter adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1);

        if(search != "" && itemType != Item.ItemType.NONE){
            for(Item i : thisLocation.getItems()){
                if(i.getShortDesc().contains(search) && i.getItemType() == itemType){
                    adapter.add(i);
                }
            }
        }else if (search != ""){
            for(Item i : thisLocation.getItems()){
                if(i.getShortDesc().contains(search)){
                    adapter.add(i);
                }
            }
        }else if (itemType != Item.ItemType.NONE){
            for(Item i : thisLocation.getItems()){
                if(i.getItemType() == itemType){
                    adapter.add(i);
                }
            }
        }else{
            adapter.addAll(thisLocation.getItems());
        }

        if(adapter.isEmpty()){
            Toast.makeText(this, "There are no items matching your search.", Toast.LENGTH_LONG).show();
        }

        ListView itemList = findViewById(R.id._items);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocationDetailActivity.this, ItemDetailActivity.class);

                Item selectedItem = thisLocation.getItems().get(position);
                intent.putExtra("Item", selectedItem);

                startActivity(intent);
            }
        });
    }


}
