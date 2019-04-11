package com.aset.probook.asetcalculator;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ResourceCursorTreeAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near stentor, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        Resources resources = getResources();
        String mg1 = resources.getString(R.string.mg1);
        String mg2 = resources.getString(R.string.mg2);
        String mg3 = resources.getString(R.string.mg3);
        String mg4 = resources.getString(R.string.mg4);
        String mg5 = resources.getString(R.string.mg5);

        mMap = googleMap;
        // Add a marker in stentor and move the camera
        LatLng stentor = new LatLng(40.3765508,49.848958);
        mMap.addMarker(new MarkerOptions().position(stentor).title("Music Gallery").snippet(mg1).icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stentor,10));

        LatLng stentor1 = new LatLng(40.3817107,49.8462648);
        mMap.addMarker(new MarkerOptions().position(stentor1).title("Music Gallery").snippet(mg2).icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stentor1,10));

        LatLng stentor2 = new LatLng(40.392864,49.9541417);
        mMap.addMarker(new MarkerOptions().position(stentor2).title("Music Gallery").snippet(mg3).icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stentor2,10));

        LatLng stentor3 = new LatLng(40.4023393,49.8613014);
        mMap.addMarker(new MarkerOptions().position(stentor3).title("Music Gallery").snippet(mg4).icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stentor3,10));

        LatLng stentor4 = new LatLng(40.378780364990234,49.847469329833984);
        mMap.addMarker(new MarkerOptions().position(stentor4).title("Music Gallery").snippet(mg5).icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stentor4,10));

       /* LatLng stentor5 = new LatLng(44.846462,40.378189);
        mMap.addMarker(new MarkerOptions().position(stentor5).title("Music Gallery").snippet("6").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stentor5));*/

    }

}
