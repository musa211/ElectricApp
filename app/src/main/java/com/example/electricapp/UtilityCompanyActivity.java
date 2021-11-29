package com.example.electricapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;

public class UtilityCompanyActivity extends AppCompatActivity {

    private final String REQUEST_URL = "https://developer.nrel.gov/api/utility_rates/v3.json"
            + "?api_key=TJvdLak5viMPS7UqrYnPKt8ROdOvmUhoMFNhbf23";

    private String receiveAddress;
    private String receiveCity;
    private String receiveState;
    private String receiveRadius;
    private String receiveLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_company);

        AsyncHttpClient client = new AsyncHttpClient();
        RecyclerView rvUtilityInfo = findViewById(R.id.rvUtilityInfo);

        Intent intent = getIntent();
        // Retrieving data from UtilityRequestActivity
        receiveAddress = intent.getStringExtra("address_key")
                .replace(" ",  "+") + ",";
        receiveCity = intent.getStringExtra("city_key")
                .replace(" ", "+") + ",";
        receiveState = intent.getStringExtra("state_key").replace(" ", "+");
        receiveRadius = intent.getStringExtra("radius_key");
        receiveLimit = intent.getStringExtra("limit_key");

        final String final_url = REQUEST_URL
                + "&address=" + receiveAddress
                + receiveCity
                + receiveState
                + "&radius=" + receiveRadius
                + "&limit=" + receiveLimit;

        client.get(final_url,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        JSONObject jsonObject = json.jsonObject;
                        Log.d("UtilityCompanyActivity", "onSuccess");
                        try {
                            JSONObject outputs = jsonObject.getJSONObject("outputs");
                            JSONArray utilityInfoJsonArray =
                                    outputs.getJSONArray("utility_info");

                            ArrayList<UtilityInfo> utilityInfoArray =
                                    new UtilityInfo().fromJsonArray(utilityInfoJsonArray);

                            UtilityInfoAdapter adapter = new UtilityInfoAdapter(utilityInfoArray);
                            rvUtilityInfo.setAdapter(adapter);
                            rvUtilityInfo.setLayoutManager(
                                    new LinearLayoutManager(UtilityCompanyActivity.this));
                        } catch (JSONException e) {
                            Log.e("UtilityCompanyActivity", "Reached JSON Exception", e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response,
                                          Throwable throwable) {
                        Log.e("UtilityCompanyActivity", "onFailure");
                    }
                });
    }
}