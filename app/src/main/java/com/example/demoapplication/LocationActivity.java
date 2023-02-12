package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView latitudeValueTextView;
    TextView longitudeValueTextView;
    Button refreshButton;
    double latitude;
    double longitude;

    Location gpsLocation = null;
    Location networkLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitudeValueTextView = findViewById(R.id.textViewLatitude);
        longitudeValueTextView = findViewById(R.id.textViewLongitude);
        refreshButton = findViewById(R.id.buttonRefresh);

        refreshButton.setOnClickListener(view -> getCurrentLocation());
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE
        }, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        boolean hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        LocationListener gpsLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                gpsLocation = location;
            }
        };

        LocationListener networkLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                networkLocation = location;
            }
        };

        if (hasGps) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    0F,
                    gpsLocationListener
            );
        }

        if (hasNetwork) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    0F,
                    networkLocationListener
            );
        }

        try {
            gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gpsLocation != null) {
            latitude = gpsLocation.getLatitude();
            longitude = gpsLocation.getLongitude();
        }
        else if (networkLocation != null) {
            latitude = networkLocation.getLatitude();
            longitude = networkLocation.getLongitude();
        }
        else {
            latitude = 0.0;
            longitude = 0.0;
        }

        setLocationValues();
    }

    private void setLocationValues() {
        latitudeValueTextView.setText(Double.toString(latitude));
        longitudeValueTextView.setText(Double.toString(longitude));
    }
}