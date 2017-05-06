package com.example.dingweichao.mapquesttutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapquest.mapping.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapboxMap;
import com.mapquest.mapping.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
public class MainActivity extends AppCompatActivity {
    private MapboxMap mMapboxMap;
    private MapView mMapView;
    private final LatLng SAN_FRAN = new LatLng(-37.9085,145.1371);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapboxAccountManager.start(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.mapquestMapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mMapboxMap = mapboxMap;
                mMapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRAN, 11));
                addMarker(mMapboxMap);
            }
        });
    }
    private void addMarker(MapboxMap mapboxMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SAN_FRAN);
        markerOptions.title("Monash University");
        markerOptions.snippet("Welcome to Monash University!");
        mapboxMap.addMarker(markerOptions);
    }
    @Override
    public void onResume()
    { super.onResume(); mMapView.onResume(); }
    @Override
    public void onPause()
    { super.onPause(); mMapView.onPause(); }
    @Override
    protected void onDestroy()
    { super.onDestroy(); mMapView.onDestroy(); }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    { super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
