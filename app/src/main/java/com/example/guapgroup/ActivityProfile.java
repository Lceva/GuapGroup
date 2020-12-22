package com.example.guapgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ActivityProfile extends AppCompatActivity {

    FirebaseAuth mAuth =FirebaseAuth.getInstance();;
    FirebaseUser user = mAuth.getCurrentUser();
    boolean  F = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView email =(TextView) findViewById(R.id.email_txt);
        email.setText(user.getEmail());
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
        mAuth.signOut();
        user = null;
        Intent intent = new Intent(ActivityProfile.this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }
}