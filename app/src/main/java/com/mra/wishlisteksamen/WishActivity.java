package com.mra.wishlisteksamen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class WishActivity extends AppCompatActivity {

    private TextView tvtitle, tvdetails;
    FirebaseFirestore db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        db = FirebaseFirestore.getInstance();

        tvtitle = findViewById(R.id.activity_wish_item_title);
        tvdetails = findViewById(R.id.activity_wish_item_details);
        btn = findViewById(R.id.btn_delete_wish);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String details = intent.getExtras().getString("Details");
        String id = intent.getExtras().getString("Id");

        tvtitle.setText(title);
        tvdetails.setText(details);

        btn.setOnClickListener(view -> {

            db.collection("wishlist").document(id).delete().addOnSuccessListener(unused -> Log.d("tag", "Deleted " + id)).addOnFailureListener(e -> Log.w("tag", "Error deleting " + id));

            Intent i = new Intent(WishActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}