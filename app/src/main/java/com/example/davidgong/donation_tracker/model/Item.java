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
        CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER
    }

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
