package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.activities.TodayExpenseActivity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent todayExpenseActivity = new Intent(MainActivity.this, TodayExpenseActivity.class);
                startActivity(todayExpenseActivity);
            }
        };

        Timer counter = new Timer();
        counter.schedule(timerTask, 3000);
    }
}
