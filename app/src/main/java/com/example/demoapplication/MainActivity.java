package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.demoapplication.helpers.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView counterTextView;
    TextView locationTextView;
    Button incrementButton;
    Button navigateButton;
    Button navigateServiceRequestsButton;
    Button createServiceRequestButton;
    ImageButton navigateToLocationImageButton;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.textViewCounter);
        incrementButton = findViewById(R.id.buttonIncrement);
        navigateButton = findViewById(R.id.buttonNavigate);
        navigateServiceRequestsButton = findViewById(R.id.buttonNavigateServiceRequests);
        createServiceRequestButton = findViewById(R.id.buttonCreateServiceRequest);
        navigateToLocationImageButton = findViewById(R.id.imageButtonLocation);
        locationTextView = findViewById(R.id.textViewLocation);

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                counterTextView.setText(Integer.toString(counter));
            }
        });

        navigateButton.setOnClickListener(view -> {
            Intent navigateIntent = new Intent(MainActivity.this, SecondActivity.class);
            navigateIntent.putExtra("counter", counter);
            startActivity(navigateIntent);
        });

        navigateToLocationImageButton.setOnClickListener(view -> {
            Intent navigateIntent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(navigateIntent);
        });

        createServiceRequestButton.setOnClickListener(view -> {
            Intent navigateIntent = new Intent(MainActivity.this, CreateServiceRequest.class);
            startActivity(navigateIntent);
        });

        navigateServiceRequestsButton.setOnClickListener(view -> {
            Intent navigateIntent = new Intent(MainActivity.this, ServiceRequestList.class);
            startActivity(navigateIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "start");

        getLocationFromSharedPreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "restart");
    }

    private void getLocationFromSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String latitude = sharedPreferences.getString(Constants.LATITUDE_KEY, "0");
        String longitude = sharedPreferences.getString(Constants.LONGITUDE_KEY, "0");

        String unknownLocationText = getResources().getString(R.string.unknown_location_text);
        String knownLocationText = getResources().getString(R.string.known_location_text);

        locationTextView.setText(latitude == "0" && longitude == "0" ? unknownLocationText
                : String.format(knownLocationText, latitude, longitude));
    }
}