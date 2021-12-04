package com.example.electricapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    Animation anim_splash;
    Animation anim_text;
    ImageView ivSplash;
    TextView tvSplash;
    TextView tvSplashSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // load animation
        anim_splash = AnimationUtils.loadAnimation(this, R.anim.anim_image);
        anim_text = AnimationUtils.loadAnimation(this, R.anim.anim_text_up);

        tvSplash = findViewById(R.id.tvSplashTitle);
        tvSplashSub = findViewById(R.id.tvSplashSubtitle);
        ivSplash = findViewById(R.id.ivSplash);

        // passing animation
        ivSplash.startAnimation(anim_splash);
        tvSplash.startAnimation(anim_text);
        tvSplashSub.startAnimation(anim_text);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}