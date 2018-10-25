package com.example.davidgong.donation_tracker.model;

public class Item {
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
}
