package com.example.guapgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityTimetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
    }

    public void onClicktimetable(View view) {
        Intent intent = new Intent(ActivityTimetable.this, ActivityTimetable.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void onClickNews(View view) {
        Intent intent = new Intent(ActivityTimetable.this, ActivityNews.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(ActivityTimetable.this, ActivityProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}