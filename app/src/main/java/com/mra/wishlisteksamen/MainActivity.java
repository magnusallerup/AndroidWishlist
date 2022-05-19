package com.mra.wishlisteksamen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnIntent;
    List<Wish> wishList;
    FirebaseFirestore firestore;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntent = findViewById(R.id.new_wish_btn);
        wishList = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();

        btnIntent.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewWishActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        firestore.collection("wishlist").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Log.d("tag", "onStart got wish with id: " + queryDocumentSnapshot.getId());

                    wishList.add(queryDocumentSnapshot.toObject(Wish.class));
                }
                wishList.sort(Wish.WishNameComparator);

        RecyclerView rv = findViewById(R.id.recyclerview_id);
        adapter = new RecyclerViewAdapter(MainActivity.this, wishList);
        rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        rv.setAdapter(adapter);
                } else {
                    Log.d("tag", "onStart Error " + task.getException());
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_title:
                wishList.sort(Wish.WishNameComparator);
                Toast.makeText(this,"Sort by name", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_category:
                wishList.sort(Wish.WishCategoryComparator);
                Toast.makeText(this,"Sort by category", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_price:
                wishList.sort(Wish.WishPriceComparator);
                Toast.makeText(this,"Sort by price", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}