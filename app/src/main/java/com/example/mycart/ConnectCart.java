package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectCart extends AppCompatActivity {

    private EditText IDtext;

    public static String CartID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IDtext = findViewById(R.id.CardIDTextField);
        CartID = "";
        setContentView(R.layout.activity_connect_cart);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }

    public void onClickSubmit_CardID(View view) {
        try {
            IDtext = findViewById(R.id.CardIDTextField);
            CartID = IDtext.getText().toString();
            Toast.makeText(this, "Connected to " + IDtext.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}