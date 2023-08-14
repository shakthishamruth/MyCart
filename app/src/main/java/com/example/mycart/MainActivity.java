package com.example.mycart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Adapter and RecyclerView
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<GroceryItem> items;

    // Startup
    Button startupLogIn;

    // Login
    private EditText userName;

    // Register
    private EditText newUser, pws1, pws2;
    private RelativeLayout parent;

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
        items.add(new GroceryItem("Card1"));
        items.add(new GroceryItem("Card2"));
        items.add(new GroceryItem("Card3"));
        items.add(new GroceryItem("Card4"));
        items.add(new GroceryItem("Card5"));
        items.add(new GroceryItem("Card6"));
        items.add(new GroceryItem("Card7"));
        items.add(new GroceryItem("Card8"));
        items.add(new GroceryItem("Card9"));

        startupLogIn = findViewById(R.id.startupLogIn);

    }

    // Startup to login or register
    public void onClickLogIn(View view) {
        layout = 1;
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        loadData();
        updateViews();
    }

    // Register to create new account
    public void onClickRegister(View view) {
        layout = -1;
        setContentView(R.layout.register);

        newUser = findViewById(R.id.userNameRegister);
        pws1 = findViewById(R.id.passwordRegister);
        pws2 = findViewById(R.id.confirmPasswordRegister);
        parent = findViewById(R.id.parent);
    }

    public void onClickCreate(View view) {
        if (pws1.getText().toString().equals("") || pws2.getText().toString().equals("")) {
            Toast.makeText(this, "Provide Password!", Toast.LENGTH_SHORT).show();
        } else if (pws1.getText().toString().equals(pws2.getText().toString())) {
            layout = 1;
            setContentView(R.layout.login);
            userName = findViewById(R.id.userName);
        } else {
            Snackbar.make(parent, "Check the password again!", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pws1.setText("");
                    pws2.setText("");
                }
            }).show();
        }
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
            setContentView(R.layout.login);
            layout = 1;
            userName = findViewById(R.id.userName);
            loadData();
            updateViews();
        } else if (layout == -1) {
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

        Toast.makeText(this, "UserName Saved!", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        userName.setText(text);
    }

}
