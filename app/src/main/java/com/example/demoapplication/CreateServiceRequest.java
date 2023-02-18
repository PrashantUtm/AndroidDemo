package com.example.demoapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.demoapplication.helpers.ServiceRequestDatabaseHelper;
import com.example.demoapplication.models.ServiceRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateServiceRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText titleEditText;
    EditText descriptionEditText;
    DatePicker datePicker;
    TimePicker timePicker;
    Button createButton;
    Spinner categorySpinner;

    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_request);

        titleEditText = findViewById(R.id.editTextTitle);
        descriptionEditText = findViewById(R.id.editTextDescription);
        datePicker = findViewById(R.id.datePickerDateIssue);
        timePicker = findViewById(R.id.timePickerTimeIssue);
        createButton = findViewById(R.id.buttonCreate);
        categorySpinner = findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        createButton.setOnClickListener(view -> {
            onCreateButtonClicked();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCategory = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void onCreateButtonClicked() {
        LocalDateTime issueDateTime = formatDate(datePicker.getDayOfMonth(), datePicker.getMonth(),
                datePicker.getYear(), timePicker.getHour(), timePicker.getMinute());

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        if(title.isEmpty() || description.isEmpty() || selectedCategory.isEmpty()) {
            Toast requiredFieldsToast = Toast.makeText(CreateServiceRequest.this, "Fill in all fields", Toast.LENGTH_LONG);
            requiredFieldsToast.show();
            return;
        }

//        String date = "22/02/2023";
//        String time = "22:02";
//        DateTimeFormatter df =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter tf =  DateTimeFormatter.ofPattern("hh:mm");
//        LocalDate localDate = LocalDate.parse(date, df);
//        LocalTime localTime = LocalTime.parse(time, tf);
//        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);

        ServiceRequestDatabaseHelper dbHelper = new ServiceRequestDatabaseHelper(this);
        ServiceRequest newServiceRequest = new ServiceRequest(title, description, selectedCategory, issueDateTime);
        dbHelper.addServiceRequest(newServiceRequest);

        Intent successNavigateIntent = new Intent(CreateServiceRequest.this, SuccessActivity.class);
        successNavigateIntent.putExtra("category", selectedCategory);
        successNavigateIntent.putExtra("issueDateTime", issueDateTime);
        startActivity(successNavigateIntent);
    }

    private LocalDateTime formatDate(int day, int month, int year, int hours, int minutes) {
        LocalDateTime date = LocalDateTime.of(year, month+1, day, hours, minutes);
        return date;
    }
}