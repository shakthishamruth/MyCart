package com.example.mycart;

import static com.example.mycart.MainActivity.layout;
import static com.example.mycart.MainActivity.mAuth;
import static com.example.mycart.MainActivity.userEmail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private TextView homeWelcomeText;
    private FirebaseUser currentUser;

    public static boolean canViewFlag = false;

    private Button home_ViewCart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_ViewCart = findViewById(R.id.home_ViewCart);
        homeWelcomeText = findViewById(R.id.home_welcomeText);
        homeWelcomeText.setText("👋 " + userEmail);
        home_ViewCart.setEnabled(canViewFlag);
    }

    public void onClickHome_Logout(View view) {
        Toast.makeText(this, userEmail + " Successfully Logged Out!", Toast.LENGTH_LONG).show();
        mAuth.signOut();
        layout = 0;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickHome_ConnectCart(View view) {
        canViewFlag = true;
        Intent intent = new Intent(getApplicationContext(), ConnectCart.class);
        startActivity(intent);
        finish();
    }

    public void onClickHome_ViewCart(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewCart.class);
        startActivity(intent);
        finish();
    }
}