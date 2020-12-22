package com.example.guapgroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ActivityLogin extends AppCompatActivity {
    Button btnSignin;
    FirebaseDatabase db;
    DatabaseReference users;
    private FirebaseAuth mAuth;
    public FirebaseUser user;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignin = findViewById(R.id.btnSignin);
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        onStart();
        btnSignin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                showSigninWindow();
            }
        });
    }

    private void showSigninWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");
        LayoutInflater inflater = LayoutInflater.from(this);
        View SigninWindow = inflater.inflate(R.layout.sing_in_window, null);
        dialog.setView(SigninWindow);

        final MaterialEditText email = SigninWindow.findViewById(R.id.email);
        final MaterialEditText password = SigninWindow.findViewById(R.id.password);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                SaveStatus(null);
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(ActivityLogin.this, "Ведите почту", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length()<5) {
                    Toast.makeText(ActivityLogin.this, "Ведите коректный пороль", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult> () {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                user = mAuth.getCurrentUser();
                                SaveStatus(user);
                                Intent intent = new Intent(ActivityLogin.this, ActivityTimetable.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityLogin.this, "Ошиибка авторизации", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.show();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Status(currentUser);
    }

    public void SaveStatus(FirebaseUser user) {
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.commit();
    }

    private void Status(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(ActivityLogin.this, ActivityTimetable.class);
            startActivity(intent);
        } else {
            btnSignin.setVisibility(View.VISIBLE);
        }
    }

}