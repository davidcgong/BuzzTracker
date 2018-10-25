package com.example.davidgong.donation_tracker;

<<<<<<< HEAD
import android.os.Parcel;
import android.os.Parcelable;

import com.example.davidgong.donation_tracker.LocationType;

public class Location implements Parcelable {
    private int key;
    private String name;
    private float lat;
    private float longitude;
    private String address;
    private String city;
    private String state;
    private int zip;
    private LocationType type;
    private String phone;
    private String website;
    private static boolean alreadyUploaded = false;

    Location(int key, String name, float lat, float longitude, String address, String city, String state, int zip, LocationType type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    Location() {
    }

    Location(Parcel in) {
        this.key = in.readInt();
        this.name = in.readString();
        this.lat = in.readFloat();
        this.longitude = in.readFloat();
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zip = in.readInt();
        this.type = in.readParcelable(LocationType.class.getClassLoader());
        this.phone = in.readString();
        this.website = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(key);
        dest.writeString(name);
        dest.writeFloat(lat);
        dest.writeFloat(longitude);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeInt(zip);
        dest.writeParcelable(type, flags);
        dest.writeString(phone);
        dest.writeString(website);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }


    public String toString() {
        return String.format("Welcome to %s, located in %s, %s", name, city, state);
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public LocationType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }


}

=======
import java.io.Serializable;

public class Location implements Serializable{

    private String locationName, locationType, streetAddress, city, state, zip, phoneNumber;
    private double latitude, longitude;

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
    }

    @Override
    public String toString() {
        return locationName + "\n" + locationType + "\n" + phoneNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
>>>>>>> David
