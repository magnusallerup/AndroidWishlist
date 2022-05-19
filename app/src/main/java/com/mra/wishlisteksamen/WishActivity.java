package com.mra.wishlisteksamen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class WishActivity extends AppCompatActivity {

    private TextView tvTitle, tvDetails, tvCategory, tvPrice;
    FirebaseFirestore db;
    Button btn_delete, btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        db = FirebaseFirestore.getInstance();

        tvTitle = findViewById(R.id.activity_wish_item_title);
        tvDetails = findViewById(R.id.activity_wish_item_details);
        tvCategory = findViewById(R.id.activity_wish_item_category);
        tvPrice = findViewById(R.id.activity_wish_item_price);
        btn_delete = findViewById(R.id.btn_delete_wish);
        btn_return = findViewById(R.id.btn_return);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String details = intent.getExtras().getString("Details");
        String id = intent.getExtras().getString("Id");
        String category = intent.getExtras().getString("Category");
        int price = (intent.getExtras().getInt("Price"));

        tvTitle.setText(String.format("Ønske: %s",title));
        tvDetails.setText(String.format("Info: %s", details));
        tvCategory.setText(String.format("Kategori: %s", category));
        tvPrice.setText(String.format("Pris: %s", price));

        btn_delete.setOnClickListener(view -> {
            db.collection("wishlist").document(id).delete()
                    .addOnSuccessListener(e -> Log.d("tag", "Deleted " + id))
                    .addOnFailureListener(e -> Log.w("tag", "Error deleting " + id));
            Intent i = new Intent(WishActivity.this, MainActivity.class);
            startActivity(i);
        });

        btn_return.setOnClickListener(view -> {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        });
    }
}