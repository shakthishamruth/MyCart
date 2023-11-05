package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ManualUpload extends AppCompatActivity {

    private TextView uploadName, uploadDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_upload);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ViewCart.class);
        startActivity(intent);
        finish();
    }

    public void uploadData(View view) {

    }
}