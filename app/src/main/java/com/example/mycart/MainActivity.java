package com.example.mycart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Adapter and RecyclerView
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;

    // Startup
    Button startupLogIn;

    // Login
    private TextView userName;

    // All
    private int layout = 0;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    // Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        items = new ArrayList<>();
        items.add("Card 1");
        items.add("Card 2");
        items.add("Card 3");
        items.add("Card 4");
        items.add("Card 5");
        items.add("Card 6");
        items.add("Card 7");
        items.add("Card 8");
        items.add("Card 9");
        items.add("Card 10");

        startupLogIn = findViewById(R.id.startupLogIn);

    }

    // Startup to login
    public void onClickLogIn(View view) {
        layout = 1;
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        loadData();
        updateViews();
    }

    // Login to activity_main
    public void onClickSubmit(View view) {
        saveData();
        layout = 2;
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, items);
        recyclerView.setAdapter(adapter);
    }


    // All
    @Override
    public void onBackPressed() {
        if (layout == 1) {
            setContentView(R.layout.startup);
            layout = 0;
        } else if (layout == 2) {
            layout = 1;
            setContentView(R.layout.login);
            userName = findViewById(R.id.userName);
            loadData();
            updateViews();
        } else {
            super.onBackPressed();
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, userName.getText().toString());
        editor.apply();

        Toast.makeText(this, "Login Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        userName.setText(text);
    }

}
