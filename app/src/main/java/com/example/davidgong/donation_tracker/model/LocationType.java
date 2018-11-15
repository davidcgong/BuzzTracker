package com.example.davidgong.donation_tracker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * the type of a location
 */
public enum LocationType implements Parcelable {
    DROP_OFF("Drop Off"), STORE("Store"), WAREHOUSE("Warehouse");

    private final String name;
    public static final Parcelable.Creator<LocationType> CREATOR = new Parcelable.Creator<LocationType>() {
        @Override
        public LocationType createFromParcel(Parcel in) {
            String inName = in.readString();
            for (LocationType type : LocationType.values()) {
                if (type.getName().equals(inName)) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public LocationType[] newArray(int size) {
            return new LocationType[size];
        }
    };

    /**
     * sets the name of this location type
     *
     * @param name the name of this location type
     */
    LocationType(String name) {
        this.name = name;
    }

    /**
     * creates new location type from Parcel
     *
     * @param in parcel to make into LocationType
     */
    LocationType(Parcel in) {
        name = in.readString();
    }

    /**
     * returns the loaction type name
     *
     * @return the loaction type name
     */
    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}