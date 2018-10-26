package com.example.davidgong.donation_tracker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocationManager {
    private List<Location> locations;
    private static final int NAME_TOKEN = 1;
    private  static final int LATATUDE_TOKEN = 2;
    private static final int LONGITUDE_TOKEN = 3;
    private static final int STREET_TOKEN = 4;
    private static final int CITY_TOKEN = 5;
    private static final int STATE_TOKEN = 6;
    private static final int ZIP_TOKEN = 7;
    private static final int TYPE_TOKEN = 8;
    private static final int PHONE_TOKEN = 9;



    public LocationManager() {
        locations = new ArrayList<>();
    }

    public LocationManager(InputStream is) {
        List<Location> locations = new ArrayList<Location>();
        String line;
        boolean firstLine = true;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while((line = br.readLine()) != null) {
                if (!firstLine) {
                    String[] values = line.split(",");
                    Location newLocation = new Location(
                            values[NAME_TOKEN],
                            LocationType.valueOf(values[
                                    TYPE_TOKEN].replaceAll("\\S", "_").toUpperCase())
                                    .toString(),
                            Double.parseDouble(values[LATATUDE_TOKEN]),
                            Double.parseDouble(values[LONGITUDE_TOKEN]),
                            values[STREET_TOKEN],
                            values[CITY_TOKEN],
                            values[STATE_TOKEN],
                            values[ZIP_TOKEN],
                            values[PHONE_TOKEN]);
                    locations.add(newLocation);
                } else {
                    firstLine = false;
                }
            }
        } catch (java.io.IOException ie) {
            //TODO : add code to fail gracefully
        }
    }
}
