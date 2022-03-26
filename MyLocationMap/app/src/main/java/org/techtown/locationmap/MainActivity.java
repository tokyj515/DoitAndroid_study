package org.techtown.locationmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });


    }



    public void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            showCurrentLocation(location);
                        }
                    }
            );
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    public void showCurrentLocation(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng curPoint = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(curPoint);
    }


    public void showMyLocationMarker(LatLng curPoint){
        MarkerOptions myLocationMarker = new MarkerOptions();
        myLocationMarker.position(curPoint);
        myLocationMarker.title("내 위치");
        myLocationMarker.snippet("GPS로 확인한 위치");
        myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));

        map.addMarker(myLocationMarker);


    }
}