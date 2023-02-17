package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;

public class SuccessActivity extends AppCompatActivity {

    Button navigateFaqButton;
    Button navigateHomeButton;
    TextView successTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        successTextView = findViewById(R.id.textViewSuccessMessage);
        navigateFaqButton = findViewById(R.id.buttonFaq);
        navigateHomeButton = findViewById(R.id.buttonHome);

        Bundle extras = getIntent().getExtras();
        if(extras.containsKey("category") && extras.containsKey("issueDateTime")) {
            LocalDateTime issueDateTime = (LocalDateTime) extras.get("issueDateTime");
            String successMessage = String.format(getString(R.string.success_message),
                    issueDateTime.toLocalDate(),
                    issueDateTime.toLocalTime(),
                    extras.getString("category"));
            successTextView.setText(successMessage);
        }

        navigateFaqButton.setOnClickListener(view -> {
            Intent faqIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://material.io/blog/announcing-material-you"));
            try {
                startActivity(faqIntent);
            } catch (ActivityNotFoundException e) {

            }
        });

        navigateHomeButton.setOnClickListener(view -> {
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show();
        });
    }
}