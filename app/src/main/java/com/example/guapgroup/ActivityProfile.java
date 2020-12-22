package com.example.guapgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityProfile extends AppCompatActivity {

    String str_email = ActivityLogin.setEmail();
    String str_password = ActivityLogin.setPassword();
    boolean  F = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView email =(TextView) findViewById(R.id.email_txt);
        TextView password =(TextView) findViewById(R.id.password_txt);
        email.setText(str_email);
        password.setText(str_password);
    }

    public void onClicktimetable(View view) {
        Intent intent = new Intent(ActivityProfile.this, ActivityTimetable.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    public void onClickNews(View view) {
        Intent intent = new Intent(ActivityProfile.this, ActivityNews.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    public void onClickExit(View view) {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onClickSowPassword(View view) {
        TextView password =(TextView) findViewById(R.id.password_txt);

        if(F){
            password.setVisibility(View.VISIBLE);
        }else {
            password.setVisibility(View.INVISIBLE);
        }

        if (F){
            F=false;
        }else {
            F = true;
        }

    }
}