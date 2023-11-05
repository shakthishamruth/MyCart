package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
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

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerView = findViewById(R.id.recyclerView);
        items = new ArrayList<>();

        cartDBRef = FirebaseDatabase.getInstance().getReference("cart1");
        cartDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    items.clear();

                    for (DataSnapshot cartDatasnap : snapshot.getChildren()) {
                        GroceryItem item = cartDatasnap.getValue(GroceryItem.class);
                        items.add(item);
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewCart.this));
                    adapter = new Adapter(ViewCart.this, items);
                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(ViewCart.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}