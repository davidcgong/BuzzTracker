package com.example.davidgong.donation_tracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public enum LocationType implements Parcelable {
    DROP_OFF ("Drop Off"), STORE("Store"), WAREHOUSE("Warehouse");

    private String name;
    public static final Parcelable.Creator<LocationType> CREATOR = new Parcelable.Creator<LocationType>() {
        public LocationType createFromParcel(Parcel in) {
            String inName = in.readString();
            for(LocationType type: LocationType.values()) {
                if(type.getName().equals(inName)) {
                    return type;
                }
            }
            return null;
        }
        public LocationType[] newArray(int size) {
            return new LocationType[size];
        }
    };

    LocationType(String name) {
        this.name = name;
    }

    LocationType(Parcel in) {
        name = in.readString();
    }

    public String getName() {return name;}

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}