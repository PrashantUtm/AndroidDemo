package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.demoapplication.helpers.ServiceRequestDatabaseHelper;
import com.example.demoapplication.helpers.ServiceRequestsAdapter;
import com.example.demoapplication.models.ServiceRequest;

import java.util.List;

public class ServiceRequestList extends AppCompatActivity {

    RecyclerView serviceRequestsRecyclerView;
    List<ServiceRequest> serviceRequests;
    ServiceRequestDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request_list);

        serviceRequestsRecyclerView = findViewById(R.id.recyclerViewServiceRequests);
        dbHelper = new ServiceRequestDatabaseHelper(this);
        serviceRequests = dbHelper.getAllServiceRequests();
        ServiceRequestsAdapter adapter = new ServiceRequestsAdapter(serviceRequests);
        serviceRequestsRecyclerView.setHasFixedSize(true);
        serviceRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceRequestsRecyclerView.setAdapter(adapter);
    }
}