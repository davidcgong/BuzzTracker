package com.example.davidgong.donation_tracker.model

import android.os.Parcel
import android.os.Parcelable

enum class LocationType : Parcelable {
    DROP_OFF("Drop Off"), STORE("Store"), WAREHOUSE("Warehouse");

    /**
     * returns the loaction type name1
     *
     * @return the loaction type name1
     */
    val name1: String?

    /**
     * sets the name1 of this location type
     *
     * @param name the name1 of this location type
     */
    private constructor(name: String) {
        this.name1 = name
    }

    /**
     * creates new location type from Parcel
     *
     * @param in parcel to make into LocationType
     */
    private constructor(`in`: Parcel) {
        name1 = `in`.readString()
    }

    override fun describeContents(): Int {
        return this.hashCode()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name1)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LocationType> = object : Parcelable.Creator<LocationType> {
            override fun createFromParcel(`in`: Parcel): LocationType? {
                val inName = `in`.readString()
                for (type in LocationType.values()) {
                    if (type.name1 == inName) {
                        return type
                    }
                }
                return null
            }

            override fun newArray(size: Int): Array<LocationType?> {
                return arrayOfNulls(size)
            }
        }
    }
}