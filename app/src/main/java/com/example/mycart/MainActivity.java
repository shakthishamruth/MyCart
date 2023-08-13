package com.example.mycart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button startupLogIn;

    private TextView userName;

    private int layout = 0;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        startupLogIn = findViewById(R.id.startupLogIn);

    }

    public void onClickLogIn(View view) {
        layout = 1;
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        loadData();
        updateViews();
    }

    public void onClickSubmit(View view) {
        saveData();
    }

    @Override
    public void onBackPressed() {
        if (layout == 1) {
            setContentView(R.layout.startup);
            layout = 0;
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
