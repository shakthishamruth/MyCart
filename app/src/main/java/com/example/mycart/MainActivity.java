package com.example.mycart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Adapter and RecyclerView
    ArrayList<GroceryItem> items;

    // Startup
    Button startupLogIn;

    // Login
    private EditText userName, password;
    private ProgressBar pb;
    private Intent intent;

    // Register
    private EditText newUser, pws1, pws2;
    private RelativeLayout parent;
    private ProgressBar registerProgressbar;

    // All
    public static int layout = 0;

    public static String userEmail;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    // Database
    public static FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userEmail = currentUser.getEmail();
            intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }
    }

    // Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        mAuth = FirebaseAuth.getInstance();

        items = new ArrayList<>();

        startupLogIn = findViewById(R.id.startupLogIn);
    }

    /**
     * MAIN *
     **/
    // Startup to login or register
    public void onClickLogIn(View view) {
        layout = 1;
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        pb = findViewById(R.id.loginProgressBar);
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
        registerProgressbar = findViewById(R.id.registerProgressbar);
    }

    /**
     * REGISTER *
     **/
    public void onClickCreate(View view) {
        registerProgressbar.setVisibility(View.VISIBLE);
        if (pws1.getText().toString().equals("") || pws2.getText().toString().equals("")) {
            Toast.makeText(this, "Provide Password!", Toast.LENGTH_SHORT).show();
        } else if (pws1.getText().toString().equals(pws2.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(newUser.getText().toString(), pws1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    registerProgressbar.setVisibility(View.INVISIBLE);
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        layout = 1;
                        setContentView(R.layout.login);
                        userName = findViewById(R.id.userName);
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                    } else {
                        // If sign in fails, display a message to the user.
                        registerProgressbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Failed.", Toast.LENGTH_SHORT).show();

                    }
                }
            });

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

    /**
     * LOGIN *
     **/
    // Login to activity_main
    public void onClickSubmit(View view) {
        saveData();
        pb.setVisibility(View.VISIBLE);

        try {
            mAuth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        pb.setVisibility(View.INVISIBLE);
                        layout = 2;

                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        assert currentUser != null;
                        userEmail = currentUser.getEmail();
                        intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        password.setText("");

                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Provide User/Password", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * ALL *
     **/
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
