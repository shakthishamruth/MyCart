package com.example.mycart;

import static com.example.mycart.ConnectCart.CartID;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {

    DatabaseReference cartDBRef;

    ArrayList<GroceryItem> items;

    androidx.recyclerview.widget.RecyclerView recyclerView;

    ProgressBar viewCartPB;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerView = findViewById(R.id.recyclerView);
        viewCartPB = findViewById(R.id.viewCartPB);
        items = new ArrayList<>();

        try {
            cartDBRef = FirebaseDatabase.getInstance().getReference(CartID);
            cartDBRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    viewCartPB.setVisibility(View.VISIBLE);

                    items.clear();
                    for (DataSnapshot cartDatasnap : snapshot.getChildren()) {
                        GroceryItem item = cartDatasnap.getValue(GroceryItem.class);
                        items.add(item);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewCart.this));
                    adapter = new Adapter(ViewCart.this, items);
                    recyclerView.setAdapter(adapter);

                    viewCartPB.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}