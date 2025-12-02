package com.hotelbookingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView locationText;
    private TextView checkInDate;
    private TextView checkOutDate;
    private TextView nightsCount;
    private MaterialButton decreaseNights;
    private MaterialButton increaseNights;
    private MaterialButton searchButton;

    private int nights = 5;
    private Calendar checkInCalendar;
    private Calendar checkOutCalendar;
    private String selectedLocation = "Around current location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Log.d(TAG, "onCreate started");
            setContentView(R.layout.activity_main);
            Log.d(TAG, "Layout R.layout.activity_main set successfully.");

            // Initialize the toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // --- THIS IS THE FIX ---
            // Add this 'if' block to disable the default title text
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }

            // Initialize calendars with default dates
            checkInCalendar = Calendar.getInstance();
            checkInCalendar.set(2024, Calendar.DECEMBER, 2); // Tue, 02 Dec

            checkOutCalendar = Calendar.getInstance();
            checkOutCalendar.set(2024, Calendar.DECEMBER, 7); // Sun, 07 Dec

            // Initialize views
            initializeViews();

            // Set initial values
            updateDates();
            updateLocation();

            // Setup click listeners
            setupClickListeners();

            Log.d(TAG, "All views initialized successfully");

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate", e);
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initializeViews() {
        locationText = findViewById(R.id.location_text);
        checkInDate = findViewById(R.id.checkInDate);
        checkOutDate = findViewById(R.id.checkOutDate);
        nightsCount = findViewById(R.id.nightsCount);
        decreaseNights = findViewById(R.id.decreaseNights);
        increaseNights = findViewById(R.id.increaseNights);
        searchButton = findViewById(R.id.searchButton);

        // Verify all views are found
        if (locationText == null || checkInDate == null || checkOutDate == null ||
                nightsCount == null || decreaseNights == null || increaseNights == null ||
                searchButton == null) {
            Log.e(TAG, "One or more views are null!");
            Toast.makeText(this, "Error: Views not found", Toast.LENGTH_LONG).show();
        }
    }

    private void setupClickListeners() {
        // Decrease nights button
        decreaseNights.setOnClickListener(v -> {
            if (nights > 1) {
                nights--;
                updateDates();
            }
        });

        // Increase nights button
        increaseNights.setOnClickListener(v -> {
            nights++;
            updateDates();
        });

        // Search button - Navigate to HotelListActivity
        searchButton.setOnClickListener(v -> {
            Log.d(TAG, "Search button clicked");
            try {
                Intent intent = new Intent(MainActivity.this, HotelListActivity.class);
                intent.putExtra("location", selectedLocation);
                intent.putExtra("checkInDate", checkInCalendar.getTimeInMillis());
                intent.putExtra("checkOutDate", checkOutCalendar.getTimeInMillis());
                intent.putExtra("nights", nights);
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "Error starting HotelListActivity", e);
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        // Location text click - Show location picker
        locationText.setOnClickListener(v -> {
            showLocationPicker();
        });

        // Check-in date click - Show date picker
        findViewById(R.id.checkInLayout).setOnClickListener(v -> {
            showCheckInDatePicker();
        });

        // Tonight hotels card click
        findViewById(R.id.tonightHotelsCard).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HotelListActivity.class);
            startActivity(intent);
        });
    }

    private void showLocationPicker() {
        String[] locations = {
                "Around current location",
                "Singapore",
                "New York, USA",
                "London, UK",
                "Paris, France",
                "Tokyo, Japan",
                "Dubai, UAE",
                "Bangkok, Thailand",
                "Hong Kong",
                "Barcelona, Spain"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Location")
                .setItems(locations, (dialog, which) -> {
                    selectedLocation = locations[which];
                    updateLocation();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showCheckInDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    checkInCalendar.set(year, month, dayOfMonth);

                    // Automatically update checkout date based on nights
                    checkOutCalendar = (Calendar) checkInCalendar.clone();
                    checkOutCalendar.add(Calendar.DAY_OF_MONTH, nights);

                    updateDates();
                },
                checkInCalendar.get(Calendar.YEAR),
                checkInCalendar.get(Calendar.MONTH),
                checkInCalendar.get(Calendar.DAY_OF_MONTH)
        );

        // Set minimum date to today
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void updateLocation() {
        locationText.setText(selectedLocation);
    }

    private void updateDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());

        // Update check-out date based on nights
        checkOutCalendar = (Calendar) checkInCalendar.clone();
        checkOutCalendar.add(Calendar.DAY_OF_MONTH, nights);

        // Update UI
        checkInDate.setText(dateFormat.format(checkInCalendar.getTime()));
        checkOutDate.setText("Check-out: " + dateFormat.format(checkOutCalendar.getTime()));
        nightsCount.setText(String.valueOf(nights));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }
}