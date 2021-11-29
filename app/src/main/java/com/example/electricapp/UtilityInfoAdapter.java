package com.example.electricapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UtilityInfoAdapter extends RecyclerView.Adapter<UtilityInfoAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView idTextView;
        TextView tvDistance;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.utility_info_name);
            idTextView = itemView.findViewById(R.id.utility_info_id);
            tvDistance = itemView.findViewById(R.id.utility_distance);
        }
    }

    private final ArrayList<UtilityInfo> utilityInfoArrayList;

    public UtilityInfoAdapter(ArrayList<UtilityInfo> utilityInfoList) {
        utilityInfoArrayList = utilityInfoList;
    }

    @NonNull
    @Override
    public UtilityInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View utilityInfoView = inflater.inflate(R.layout.item_utility_info, parent, false);
        return new ViewHolder(utilityInfoView);
    }

    @Override
    public void onBindViewHolder(@NonNull UtilityInfoAdapter.ViewHolder holder, int position) {
        UtilityInfo utilityInfo = utilityInfoArrayList.get(position);

        TextView textView = holder.nameTextView;
        textView.setText("Name:  " + utilityInfo.getUtility_name());

        TextView textView1 = holder.idTextView;
        textView1.setText("ID:  " + utilityInfo.getCompany_id());

        TextView textView2 = holder.tvDistance;
        textView2.setText("Distance:  "+ utilityInfo.getDistance());
    }

    @Override
    public int getItemCount() {
        return utilityInfoArrayList.size();
    }
}