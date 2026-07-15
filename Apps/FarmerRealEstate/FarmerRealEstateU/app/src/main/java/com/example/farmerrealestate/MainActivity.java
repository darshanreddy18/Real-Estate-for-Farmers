package com.example.farmerrealestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ImageView logo = findViewById(R.id.logo_view);
        TextView appName = findViewById(R.id.app_name_view);
        TextView tagline = findViewById(R.id.tagline_view);

        // Animate Logo
        logo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1000)
            .setStartDelay(200)
            .start();

        // Animate App Name
        appName.animate()
            .alpha(1f)
            .setDuration(800)
            .setStartDelay(800)
            .start();

        // Animate Tagline
        tagline.animate()
            .alpha(1f)
            .setDuration(800)
            .setStartDelay(1200)
            .start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 3500);
    }
}