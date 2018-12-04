package com.example.davidgong.donation_tracker.controllers;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.davidgong.donation_tracker.R;
import com.example.davidgong.donation_tracker.model.Location;
import com.example.davidgong.donation_tracker.model.Model;
import com.google.android.gms.maps.GoogleMap;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;

import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    // no Google Map
    // below is HERE map
    private Map map;
    private Model model;
    private List<ViewObject> selectedMarkers;
    private com.here.android.mpa.mapping.MapFragment mapFragment;

    private static final String TAG = "MapsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        model = Model.getInstance();

        HERE_map_init();

    }

    private void HERE_map_init() {
        // Search for the map fragment to finish setup by calling init().
        mapFragment = (com.here.android.mpa.mapping.MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);

        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(Error error) {
                if (error == Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    // Set the map center to the first location we get from our loop and add markers
                    // Think about making this moreintelligent for most frequented location or closest location? if we ever get the chance
                    int counter = 0;
                    for (Location location : model.getLocations()) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        GeoCoordinate currentCoordinates = new GeoCoordinate(latitude, longitude);
                        if (counter == 0) {
                            map.setCenter(currentCoordinates, Map.Animation.NONE);
                        }
                        // Add location marker to map
                        MapMarker mapMarker = new MapMarker();
                        mapMarker.setCoordinate(currentCoordinates);
                        mapMarker.setTitle(location.getLocationName());
                        mapMarker.setDescription(location.getLocationType() + " Location\n" + "Phone Number: " + location.getPhoneNumber());
                        Log.d("MapsActivity", "Location " + counter + ": " + mapMarker.getTitle());
                        map.addMapObject(mapMarker);
                        counter++;
                    }
                    // Set the zoom level to more zoomed
                    map.setZoomLevel((map.getMinZoomLevel() / 1.5));
                    //create listener


                    // after everything initialized, we add gesture listener

                    MapGesture.OnGestureListener listener = new MapGesture.OnGestureListener.OnGestureListenerAdapter() {
                        @Override
                        public boolean onTapEvent(PointF pointF) {
                            Log.d("MapActivity", "Tap registered");
                            return super.onTapEvent(pointF);
                        }

                        @Override
                        public boolean onMapObjectsSelected(List<ViewObject> list) {
                            for (ViewObject viewObj : list) {
                                if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                                    if (((MapObject) viewObj).getType() == MapObject.Type.MARKER) {
                                        // if what we selected was a map marker
                                        MapMarker selectedMarker = (MapMarker) viewObj;
                                        // if details are not visible, then show the info bubble
                                        if (!selectedMarker.isInfoBubbleVisible()) {
                                            selectedMarker.showInfoBubble();
                                        } else {
                                            // else if the user pressed it again to close, then hide info bubble
                                            selectedMarker.hideInfoBubble();
                                        }

                                    }
                                }
                            }
                            // return false to allow the map to handle this callback also
                            return false;
                        }
                    };
                    mapFragment.getMapGesture().addOnGestureListener(listener);

                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });
    }

}
