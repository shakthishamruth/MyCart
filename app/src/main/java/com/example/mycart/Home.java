package com.example.mycart;

import static com.example.mycart.MainActivity.layout;
import static com.example.mycart.MainActivity.mAuth;
import static com.example.mycart.MainActivity.userEmail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private TextView homeWelcomeText;
    private FirebaseUser currentUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeWelcomeText = findViewById(R.id.home_welcomeText);
        homeWelcomeText.setText("Welcome " + userEmail);
    }

    public void onClickHome_Logout(View view) {
        Toast.makeText(this, userEmail + " Successfully Logged Out!", Toast.LENGTH_LONG).show();
        mAuth.signOut();
        layout = 0;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}