package com.example.mycart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button startupLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        startupLogIn = findViewById(R.id.startupLogIn);

    }

    public void onClickLogIn(View view) {
        setContentView(R.layout.login);
    }
}