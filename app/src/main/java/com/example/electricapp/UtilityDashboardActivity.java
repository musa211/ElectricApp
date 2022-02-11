package com.example.electricapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UtilityDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardOne;
    private CardView cardTwo;
    private CardView cardThree;
    private CardView cardFour;

    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_dashboard);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        cardOne = findViewById(R.id.card1);
        cardTwo = findViewById(R.id.card2);
        cardThree = findViewById(R.id.card3);
        cardFour = findViewById(R.id.card4);

        cardOne.setOnClickListener(this);
        cardTwo.setOnClickListener(this);
        cardThree.setOnClickListener(this);
        cardFour.setOnClickListener(this);

        btnLogout = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(UtilityDashboardActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {

            case R.id.card1:
                intent = new Intent(UtilityDashboardActivity.this,
                        UtilityRequestActivity.class);
                startActivity(intent);
                break;

            case R.id.card2:
                intent = new Intent(UtilityDashboardActivity.this,
                        UtilityRequestRateActivity.class);
                startActivity(intent);
                break;

            case R.id.card3:

            case R.id.card4:
        }
    }
}