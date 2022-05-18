package com.mra.wishlisteksamen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnIntent;
    List<Wish> wishList;
    FirebaseFirestore databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntent = findViewById(R.id.new_wish_btn);
        wishList = new ArrayList<>();
        databaseReference = FirebaseFirestore.getInstance();

        btnIntent.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewWishActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.collection("wishlist").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Log.d("tag", "onStart got wish with id: " + queryDocumentSnapshot.getId());
                    wishList.add(queryDocumentSnapshot.toObject(Wish.class));
                }

        RecyclerView rv =findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter adapter =new RecyclerViewAdapter(MainActivity.this, wishList);
        rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        rv.setAdapter(adapter);
                } else {
                    Log.d("tag", "onStart Error " + task.getException());
                }
            });
    }
}