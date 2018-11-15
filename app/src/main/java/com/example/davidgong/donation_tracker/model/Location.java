package com.example.davidgong.donation_tracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Location implements Serializable {
    private String locationName, locationType, streetAddress, city, state, zip, phoneNumber;
    private double latitude, longitude;

    private List<Item> items;

    /**
     * creates a new Location
     * @param locationName the name of this location
     * @param locationType the location type of this location
     * @param latitude the latitude of this location
     * @param longitude the longitude of this location
     * @param streetAddress the street address of this location
     * @param city the city where this location resides
     * @param state the state where this location resides
     * @param zip the ZIP code of this location
     * @param phoneNumber the phone number for this location
     */
    public Location(String locationName, String locationType, double latitude, double longitude,
                    String streetAddress, String city, String state, String zip, String phoneNumber) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;

        items = new ArrayList<Item>();
    }

    /**
     * default constructor of location
     */
    public Location(){
    }

    @Override
    public String toString() {
        return locationName + "\n" + locationType + "\n" + phoneNumber;
    }

    /**
     * returns the name of this location
     * @return the name of this location
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * returns the location type of this location
     * @return the location type of this location
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * returns the street address of this location
     * @return the street address of this location
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * returns the city where this location resides
     * @return the city where this location resides
     */
    public String getCity() {
        return city;
    }

    /**
     * returns the state where this location resides
     * @return the state where this location resides
     */
    public String getState() {
        return state;
    }

    /**
     * returns the ZIP code of this location
     * @return the ZIP code of this location
     */
    public String getZip() {
        return zip;
    }

    /**
     * returns the phone number of this location
     * @return the phone number of this location
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * returns the latitude of this location
     * @return the latitude of this location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * returns the longitude of this location
     * @return the longitude of this location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * returns the items at this location
     * @return the items at this location
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * sets the name of this location
     * @param locationName the name of this location
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * sets th location type of this location
     * @param locationType the location type of this location
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * sets this stree address of this location
     * @param streetAddress thie street address of this location
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * sets the city where this location resides
     * @param city the city where this location resides
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * sets the state where this location resides
     * @param state the state where this location resides
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * sets the ZIP code of this location
     * @param zip the ZIP code of this location
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * sets the phone number of this location
     * @param phoneNumber the phone number of this location
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * sets the latitude of this location
     * @param latitude the latitude of this location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * sets the longitude of this location
     * @param longitude the longitude of this location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * adds a item to this location
     * @param newItem item to add to this location
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }
}
