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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Adapter and RecyclerView
    RecyclerView recyclerView;
    Adapter adapter;
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
        items.add(new GroceryItem("Panner", "100g", "https://milkpot.com/wp-content/uploads/2022/11/Artboard-1-768x768.png"));
        items.add(new GroceryItem("Wheat", "1kg", "https://e7.pngegg.com/pngimages/4/305/png-clipart-atta-flour-whole-wheat-flour-flour-food-nutrition.png"));
        items.add(new GroceryItem("Cheese", "500g", "https://static.wixstatic.com/media/f0fada_cd2a581765ec4111a6595a3e8a450d9b~mv2.png/v1/crop/x_0,y_0,w_685,h_324/fill/w_714,h_338,al_c,lg_1,q_85,enc_auto/Cheese%20Block.png"));
        items.add(new GroceryItem("Maida", "1kg", "https://osiamart.com/image/cache/catalog/ProductImage/FMCG/FMCG/Loose/1645383082579_variant_5c135ae66994164bc9f7fde8_1-550x550.png"));
        items.add(new GroceryItem("Bread", "", "https://pngimg.com/uploads/bread/bread_PNG2281.png"));
        items.add(new GroceryItem("Milk", "1L", "https://www.bigbasket.com/media/uploads/p/xxl/70001832_2-amul-taaza-fresh-toned-milk.jpg"));

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

                    /*
                    setContentView(R.layout.activity_main);
                    recyclerView = findViewById(R.id.recyclerView);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new Adapter(MainActivity.this, items);
                    recyclerView.setAdapter(adapter);
                    */

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
