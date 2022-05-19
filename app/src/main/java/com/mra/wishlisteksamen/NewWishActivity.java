package com.mra.wishlisteksamen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class NewWishActivity extends AppCompatActivity {

    EditText editName;
    EditText editPrice;
    EditText editDetails;
    Button btnSumbit;
    Button btnCancel;
    Spinner spinner;
    FirebaseFirestore databaseReference;
    List<Wish> wishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wish);
        editName = findViewById(R.id.editTextWish);
        btnSumbit = findViewById(R.id.btnSubmit);
        databaseReference = FirebaseFirestore.getInstance();
        spinner = findViewById(R.id.spinnerCategory);
        editPrice = findViewById(R.id.editTextPrice);
        editDetails = findViewById(R.id.editTextDetails);
        btnCancel = findViewById(R.id.btnIntent);
        wishList = new ArrayList<>();
        btnSumbit.setOnClickListener(x -> addWish());

        btnCancel.setOnClickListener(view -> {
            Intent i = new Intent(NewWishActivity.this, MainActivity.class);
            startActivity(i);

        });


    }

    private void addWish(){
        String name = editName.getText().toString().trim();
        String details = editDetails.getText().toString();
        int price = Integer.parseInt(editPrice.getText().toString());
        String category = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
            String id = databaseReference.collection("wishlist").document().getId();
            System.out.println(id);

            Wish emp = new Wish(id,name,details,category, price);

            databaseReference.collection("wishlist").document(id).set(emp);

            Intent i = new Intent(NewWishActivity.this, MainActivity.class);
            startActivity(i);

            Toast.makeText(this, "Ønske tilføjet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Udfyld detaljer", Toast.LENGTH_SHORT).show();
        }



        databaseReference.collection("wishlist").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Log.d("tag", "onStart got wish with id: " + queryDocumentSnapshot.getId());
                    wishList.add(queryDocumentSnapshot.toObject(Wish.class));
                }


            } else {
                Log.d("tag", "onStart Error " + task.getException());
            }
        });
    }
}