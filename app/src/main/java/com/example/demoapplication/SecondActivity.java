package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
    Button displayFullNameButton;
    TextView fullNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        displayFullNameButton = findViewById(R.id.buttonDisplayFullName);
        fullNameTextView = findViewById(R.id.textViewFullName);

        displayFullNameButton.setOnClickListener(view -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String fullName = firstName + ' '+ lastName;
            fullNameTextView.setText(fullName);
        });
    }
}