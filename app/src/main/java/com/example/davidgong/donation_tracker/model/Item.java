package com.example.davidgong.donation_tracker.model;

import java.io.Serializable;

public class Item implements Serializable{
    private int day, month, year;
    private int hour, minute;
    private Location location;
    private String shortDesc, longDesc, value;
    //private double value;
    private ItemType itemType;

    public enum ItemType {
        NONE, CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER
    }

    /**
     * creates a new Item
     * @param day the day this item was donated
     * @param month the month this item was donated
     * @param year the year this item was donated
     * @param hour the hour this item was donated
     * @param minute the minute this item was donated
     * @param location the location where this item was donated
     * @param shortDesc a short description of this item
     * @param longDesc a long description of this item
     * @param value the value of this item
     * @param itemType the type of this item
     */
    public Item(int day, int month, int year, int hour, int minute, Location location, String shortDesc, String longDesc, String value, ItemType itemType) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.location = location;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.value = value;
        this.itemType = itemType;
    }

    @Override
    public String toString(){
        return itemType.name() + ": " + shortDesc + " (" + month + "/" + day + "/" + year + " " + hour + ":" + minute + ")";
        /*
        return "Added on " + month + "/" + day + "/" + year + " at " + hour + ":" + minute + ".\n" +
                "Location: " + location.getLocationName() + "\n" +
                "Description: " + longDesc + "\n" +
                "Value: " + value + "\n" +
                "Category: " + itemType.name();
        */
    }

    /**
     * returns the day this item was donated
     * @return day item was donated
     */
    public int getDay() {
        return day;
    }

    /**
     * sets the day the item was donated
     * @param day the day the item
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * returns the month this item was donated
     * @return the month this item was donated
     */
    public int getMonth() {
        return month;
    }

    /**
     * sets the month this item was donated
     * @param month the month this item was donated
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * returns the year this item was donated
     * @return the year this item was donated
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year this item was donated
     * @param year the year this item was donated
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns the hour this item was donated
     * @return the hour this item was donated
     */
    public int getHour() {
        return hour;
    }

    /**
     * sets the hour this item was donated
     * @param hour the hour this item was donated
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * returns the minute this item wsa donated
     * @return the minute this item wsa donated
     */
    public int getMinute() {
        return minute;
    }

    /**
     * sets the minute this item was donated
     * @param minute the minute this item was donated
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * returns the location this item was donated
     * @return the location this item was donated
     */
    public Location getLocation() {
        return location;
    }

    /**
     * sets the location this item was donated
     * @param location the location where this item was donated
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * returns the short description of this item
     * @return the short description of this item
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * sets the short description of this item
     * @param shortDesc the short description of this item
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * returns the long description of this item
     * @return the long description of this item
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * sets the long description of this item
     * @param longDesc the long description of this item
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    /**
     * returns the value of this item
     * @return the value of this item
     */
    public String getValue() {
        return value;
    }

    /**
     * sets the value of this item
     * @param value the value of this item
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * returns the item type of this item
     * @return the item type of this item
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * sets the item type of this item
     * @param itemType the item type of this item
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
