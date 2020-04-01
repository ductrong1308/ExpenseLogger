package com.example.expenselogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;

import com.example.expenselogger.activities.LoginActivity;
import com.example.expenselogger.activities.MainActivity;
import com.example.expenselogger.utils.AppUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int userId = AppUtils.GetCurrentLoggedInUserId(this);
        final Intent nextIntent;
        if(userId > 0){
            nextIntent = new Intent(SplashScreen.this, MainActivity.class);
        }
        else {
            nextIntent = new Intent(SplashScreen.this, LoginActivity.class);
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(nextIntent);
            }
        };

        // Run timerTask after delaying 3 seconds
        Timer counter = new Timer();
        counter.schedule(timerTask, 2000);
    }
}
