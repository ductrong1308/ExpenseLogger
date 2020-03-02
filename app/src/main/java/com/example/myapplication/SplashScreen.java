package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.activities.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent rateCalculatorRateIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(rateCalculatorRateIntent);
            }
        };

        // Run timerTask after delaying 3 seconds
        Timer counter = new Timer();
        counter.schedule(timerTask, 3000);
    }
}
