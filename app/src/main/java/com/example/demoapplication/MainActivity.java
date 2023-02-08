package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
            startActivity(navigateIntent);
        });
    }
}