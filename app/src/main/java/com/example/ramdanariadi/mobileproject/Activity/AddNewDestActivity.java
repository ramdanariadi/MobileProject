package com.example.ramdanariadi.mobileproject.Activity;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ramdanariadi.mobileproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddNewDestActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    GoogleMap map;
    LocationManager locationManager = null;
    Marker marker = null;
    String provider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dest);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentmap);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if(isProviderAvailable() && (provider != null)){
            locateCurrentPosition();
        }
    }

    void locateCurrentPosition(){
        int status = getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,getPackageName());
        if(status == PackageManager.PERMISSION_GRANTED){
            Location location = locationManager.getLastKnownLocation(provider);
            updateWithNewLocation(location);

            long minTime = 5000;
            float minDist = 5.0f;
            locationManager.requestLocationUpdates(provider,minTime,minDist,this);
        }
    }

    boolean isProviderAvailable(){
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);

        provider = locationManager.getBestProvider(criteria,true);
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;

            return true;
        }

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
            return  true;
        }

        if(provider != null){
            return true;
        }

        return false;
    }

    void updateWithNewLocation(Location location){
        if(location != null && provider != null){
            double lng = location.getLongitude();
            double lat = location.getLatitude();

            addBoundaryToCurrentPositoion(lat,lng);

            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat,lng)).zoom(10f).build();

            if(map != null){
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }else {
            Log.d("location error","Something went wrong");
        }
    }

    void addBoundaryToCurrentPositoion(double lat, double lang){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat,lang));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_marker));
        markerOptions.anchor(0.5f,0.5f);

        CircleOptions circleOptions = new CircleOptions().center(new LatLng(lat,lang)).radius(10000).strokeColor(0x110000FF).strokeWidth(0x110000FF);
        map.addCircle(circleOptions);
        if(marker != null)
            marker.remove();
            marker = map.addMarker(markerOptions);

    }


    @Override
    public void onLocationChanged(Location location) {
        updateWithNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
            case LocationProvider.AVAILABLE:
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        updateWithNewLocation(null);
    }
}
