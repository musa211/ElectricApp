package com.example.electricapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class UtilityRequestActivity extends AppCompatActivity {

    private EditText etAddress;
    private EditText etCity;
    private EditText etState;
    private EditText etRadius;
    private EditText etLimit;
    private Button btnSubmit;
    private Button btnClearRequest;
    private ProgressBar requestProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_request);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        etAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etRadius = findViewById(R.id.etRadius);
        etLimit = findViewById(R.id.etLimit);
        btnSubmit = findViewById(R.id.btnSubmitRequest);
        btnClearRequest = findViewById(R.id.btnClearRequest);

        requestProgress = findViewById(R.id.requestProgress);
        requestProgress.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = etAddress.getText().toString();
                String city = etCity.getText().toString();
                String state = etState.getText().toString();
                String radius = etRadius.getText().toString();
                String limit = etLimit.getText().toString();

                Intent intent = new Intent(UtilityRequestActivity.this,
                        UtilityCompanyActivity.class);

                intent.putExtra("address_key", address);
                intent.putExtra("city_key", city);
                intent.putExtra("state_key", state);
                intent.putExtra("radius_key", radius);
                intent.putExtra("limit_key", limit);

                startActivity(intent);
            }
        });

        btnClearRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAddress.getText().clear();
                etCity.getText().clear();
                etState.getText().clear();
                etRadius.getText().clear();
                etLimit.getText().clear();
            }
        });
    }
}