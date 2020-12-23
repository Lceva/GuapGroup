package com.example.guapgroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityNews extends AppCompatActivity {

    private static final int MAX_MESSAGE_LENGTH = 1000;
    private RecyclerView chatView;
    private EditText inputMessage;
    FirebaseDatabase Db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = Db.getReference("messages");
    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageButton btnSend = (ImageButton) findViewById(R.id.send);
        chatView = (RecyclerView) findViewById(R.id.chat);
        inputMessage = (EditText) findViewById(R.id.write_message);
        chatView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter dataAdapter = new DataAdapter(this, messages);
        chatView.setAdapter(dataAdapter);

        btnSend.setOnClickListener(v -> {
            String msg = inputMessage.getText().toString();
            if (msg.equals("")) {
                return;
            }
            if (msg.length() > MAX_MESSAGE_LENGTH) {
                return;
            }
            myRef.push().setValue(msg);
            inputMessage.setText("");
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                String msg = snapshot.getValue(String.class);
                messages.add(msg);
                dataAdapter.notifyDataSetChanged();
                chatView.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot,
                                       @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClicktimetable(View view) {
        Intent intent = new Intent(ActivityNews.this, ActivityTimetable.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(ActivityNews.this, ActivityProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

}