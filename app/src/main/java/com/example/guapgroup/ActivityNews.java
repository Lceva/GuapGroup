package com.example.guapgroup;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ActivityNews extends AppCompatActivity {

    private ListView chatView;
    private EditText inputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageButton btnSend = (ImageButton) findViewById(R.id.send);
        chatView = (ListView) findViewById(R.id.chat);
        inputMessage = (EditText) findViewById(R.id.write_message);

        showChat();
    }

    private void showChat() {

        Query query = FirebaseDatabase.getInstance().getReference().child("chats");
        FirebaseListOptions < MassageArea > options = new FirebaseListOptions.Builder < MassageArea > ()
                .setQuery(query, MassageArea.class)
                .setLayout(R.layout.item_message)
                .build();
        FirebaseListAdapter < MassageArea > adapter = new FirebaseListAdapter < MassageArea > (options) {
            @Override
            protected void populateView(View v, MassageArea model, int position) {

                TextView messageText = (TextView) v.findViewById(R.id.massage);
                TextView messageTime = (TextView) v.findViewById(R.id.data);
                TextView messageUser = (TextView) v.findViewById(R.id.name);

                messageText.setText(model.getMassageText());
                messageUser.setText(model.getMassageUser());
                messageTime.setText(DateFormat.format("HH:mm", model.getMessageTime()));
            }
        };

        chatView.setAdapter(adapter);
    }
    public void onBtnSendClick(View view) {
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new MassageArea(inputMessage.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        inputMessage.setText("");
    }

    public void onClicktimetable(View view) {
        Intent intent = new Intent(ActivityNews.this, ActivityTimetable.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(ActivityNews.this, ActivityProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}