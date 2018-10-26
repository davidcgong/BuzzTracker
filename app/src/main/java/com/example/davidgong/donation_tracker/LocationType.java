package com.example.davidgong.donation_tracker;

public enum LocationType {
    DROP_OFF("Drop-off"),
    STORE("Store"),
    WAREHOUSE("Warehouse");

    private String displayName;
    private LocationType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
