package com.example.demoapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    EditText firstNameEditText;
    EditText lastNameEditText;
    Button displayFullNameButton;
    TextView fullNameTextView;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("counter")) {
            counter = extras.getInt("counter");
        }

        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        displayFullNameButton = findViewById(R.id.buttonDisplayFullName);
        fullNameTextView = findViewById(R.id.textViewFullName);
        fullNameTextView.setText(Integer.toString(counter));

        displayFullNameButton.setOnClickListener(view -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String fullName = firstName + ' '+ lastName;
            fullNameTextView.setText(fullName);

            shareFullName();
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

    private void shareFullName() {
        Toast toast = Toast.makeText(this, "Full name not shared", Toast.LENGTH_SHORT);

        AlertDialog alertDialog =  new AlertDialog.Builder(SecondActivity.this).create();
        alertDialog.setTitle("Share message");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Share", (dialogInterface, i) -> {
            String fullName = fullNameTextView.getText().toString();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, fullName);
            shareIntent.setType("text/plain");
            try {
                startActivity(shareIntent);
            } catch (ActivityNotFoundException e) {
                toast.show();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (dialogInterface, i) -> {
            alertDialog.dismiss();
            toast.show();
        });

        alertDialog.show();
    }
}