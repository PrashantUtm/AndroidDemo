package com.example.demoapplication.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapplication.R;
import com.example.demoapplication.models.ServiceRequest;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServiceRequestsAdapter extends RecyclerView.Adapter<ServiceRequestsAdapter.ViewHolder> {

    private List<ServiceRequest> serviceRequests;

    public ServiceRequestsAdapter(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View serviceRequestItemView = layoutInflater.inflate(R.layout.service_request_item, parent, false);
        return new ViewHolder(serviceRequestItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ServiceRequest serviceRequest = serviceRequests.get(position);
        holder.titleTextView.setText(serviceRequest.getTitle());
        holder.categoryTextView.setText(serviceRequest.getCategory());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm");
        holder.issueDateTimeTextView.setText(serviceRequest.getIssueDateTime().format(formatter));

        holder.serviceRequestConstraintLayout.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), serviceRequest.getDescription(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return serviceRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView categoryTextView;
        public TextView issueDateTimeTextView;
        public ConstraintLayout serviceRequestConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
            issueDateTimeTextView = itemView.findViewById(R.id.textViewIssueDate);
            serviceRequestConstraintLayout = itemView.findViewById(R.id.contraintLayoutServiceRequest);
        }
    }
}
