package com.example.demoapplication.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demoapplication.models.ServiceRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequestDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ServiceRequestsDatabase";
    private static final String SERVICE_REQUESTS_TABLE_NAME = "service_requests";
    private static final String SERVICE_REQUESTS_TABLE_ID = "id";
    private static final String SERVICE_REQUESTS_TABLE_TITLE = "title";
    private static final String SERVICE_REQUESTS_TABLE_DESCRIPTION = "description";
    private static final String SERVICE_REQUESTS_TABLE_CATEGORY = "category";
    private static final String SERVICE_REQUESTS_TABLE_ISSUE_DATE = "issue_date";

    public ServiceRequestDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + SERVICE_REQUESTS_TABLE_NAME +
                "(id TEXT PRIMARY KEY, title TEXT, description TEXT, category TEXT, issue_date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SERVICE_REQUESTS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SERVICE_REQUESTS_TABLE_ID, serviceRequest.getId());
        values.put(SERVICE_REQUESTS_TABLE_TITLE, serviceRequest.getTitle());
        values.put(SERVICE_REQUESTS_TABLE_DESCRIPTION, serviceRequest.getDescription());
        values.put(SERVICE_REQUESTS_TABLE_CATEGORY, serviceRequest.getCategory());
        values.put(SERVICE_REQUESTS_TABLE_ISSUE_DATE, serviceRequest.getIssueDateTime().toString());

        db.insert(SERVICE_REQUESTS_TABLE_NAME, null, values);
        db.close();
    }

    public List<ServiceRequest> getAllServiceRequests() {
        List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SERVICE_REQUESTS_TABLE_NAME, null);

        if(cursor.moveToFirst()) {
            do {
                //"2023-02-18T11:37"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                ServiceRequest serviceRequest = new ServiceRequest(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        LocalDateTime.parse(cursor.getString(4), formatter)
                );
                serviceRequests.add(serviceRequest);
            } while(cursor.moveToNext());
        }

        return serviceRequests;
    }
}
