package com.example.electricapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class UtilityRequestRateActivity extends AppCompatActivity {

    private static final String REQUEST_URL = "https://developer.nrel.gov/api/utility_rates/v3.json"
            + "?api_key=TJvdLak5viMPS7UqrYnPKt8ROdOvmUhoMFNhbf23";

    private EditText etAddress;
    private EditText etCity;
    private EditText etState;
    private Button btnSubmitRateRequest;
    private Button btnClearRateRequest;
    private TextView comm;
    private TextView inds;
    private TextView resd;

    private String receiveAddress;
    private String receiveCity;
    private String receiveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_request_rate);

        AsyncHttpClient client = new AsyncHttpClient();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        etAddress = findViewById(R.id.etStreetAddressForRate);
        etCity = findViewById(R.id.etCityForRate);
        etState = findViewById(R.id.etStateForRate);
        btnSubmitRateRequest = findViewById(R.id.btnSubmitRateRequest);
        btnClearRateRequest = findViewById(R.id.btnClearRateRequest);

        comm = findViewById(R.id.tvCommercial);
        inds = findViewById(R.id.tvIndustrial);
        resd = findViewById(R.id.tvResidential);

        btnSubmitRateRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiveAddress = etAddress.getText().toString().replace(" ", "+") + ",";
                receiveCity = etCity.getText().toString().replace(" ", "+") + ",";
                receiveState = etState.getText().toString().replace(" ", "+");

                final String final_url = REQUEST_URL + "&address=" + receiveAddress
                        + receiveCity
                        + receiveState;

                client.get(final_url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.d("UTILITY RATE", "onSuccess");
                        JSONObject jsonObject = json.jsonObject;

                        try {
                            JSONObject outputs = jsonObject.getJSONObject("outputs");
                            comm.setText("Commercial:  " + outputs.getString("commercial"));
                            inds.setText("Industrial:  " + outputs.getString("industrial"));
                            resd.setText("Residential:  " + outputs.getString("residential"));
                        } catch (JSONException e) {
                            Log.e("UtilityRequestRateActivity ",
                                    "Reached JSON Exception", e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.d("UtilityRequestRateActivity", "onFailure");
                    }
                });
            }
        });

        btnClearRateRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAddress.getText().clear();
                etCity.getText().clear();
                etState.getText().clear();
                comm.setText("Commercial:  ");
                inds.setText("Industrial:  ");
                resd.setText("Residential:  ");
            }
        });
    }
}