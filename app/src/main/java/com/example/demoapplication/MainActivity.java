package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView counterTextView;
    Button incrementButton;
    Button navigateButton;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.textViewCounter);
        incrementButton = findViewById(R.id.buttonIncrement);
        navigateButton = findViewById(R.id.buttonNavigate);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "start");
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
}