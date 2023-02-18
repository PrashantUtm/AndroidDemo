package com.example.demoapplication.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceRequest {
    private String id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime issueDateTime;

    public ServiceRequest(String title, String description, String category, LocalDateTime issueDateTime) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.category = category;
        this.issueDateTime = issueDateTime;
    }

    public ServiceRequest(String id, String title, String description, String category, LocalDateTime issueDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.issueDateTime = issueDateTime;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }
}
