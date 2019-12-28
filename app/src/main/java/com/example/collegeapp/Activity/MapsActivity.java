package com.example.collegeapp.Activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.example.collegeapp.Model.LatitudeLongitude;
import com.example.collegeapp.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG="APP_FLOW---->";
    GoogleApiClient mGoogleApiClient;
    private LocationManager locationManager;
    //private LocationRequest mLocationRequest;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        mGoogleApiClient=new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng softwarica = new LatLng(27.7059272, 85.3302047);
        // mMap.addMarker(new MarkerOptions().position(softwarica).title("Marker in Softwarica College of It and E-commerce"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(softwarica));

        List<LatitudeLongitude> latlngs =new ArrayList<>();
        latlngs.add(new LatitudeLongitude(27.7059272,85.3302047,"SOftwarica COllege"));
        latlngs.add(new LatitudeLongitude(27.7059272,86.3302047,"SOftwarica COllege"));

        CameraUpdate center , zoom;
        for (int i=0; i<latlngs.size();i++)
        {
            center = CameraUpdateFactory.newLatLng(new LatLng(latlngs.get(i).getLat(),latlngs.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(16);
            mMap.addMarker(new MarkerOptions().position(new LatLng(latlngs.get(i).getLat(),latlngs.get(i).getLon())).title(latlngs.get(i).getMarker()));
            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);

        }
    }
}
