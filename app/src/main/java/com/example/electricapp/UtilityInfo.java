package com.example.electricapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UtilityInfo {

    private String distance;
    private String company_id;
    private String utility_name;

    public UtilityInfo(JSONObject jsonObject) throws JSONException {
        distance = jsonObject.getString("distance");
        company_id = jsonObject.getString("company_id");
        utility_name = jsonObject.getString("utility_name");
    }

    public UtilityInfo() {
    }

    public ArrayList<UtilityInfo> fromJsonArray(JSONArray utilityInfoJsonArray)
            throws JSONException {
        ArrayList<UtilityInfo> utilityInfo = new ArrayList<>();
        for (int i = 0; i < utilityInfoJsonArray.length(); i++) {
            utilityInfo.add(new UtilityInfo(utilityInfoJsonArray.getJSONObject(i)));
        }
        return utilityInfo;
    }

    public String getDistance() {
        return distance;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getUtility_name() {
        return utility_name;
    }
}