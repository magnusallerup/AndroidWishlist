package com.mra.wishlisteksamen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Wish> wishList;

    public RecyclerViewAdapter(Context context, List<Wish> data) {
        this.context = context;
        this.wishList = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_item_title.setText(wishList.get(position).getTitle());
        holder.tv_item_price.setText(wishList.get(position).toString());
        holder.tv_item_category.setText(wishList.get(position).getCategory());

        holder.cardView.setOnClickListener(view -> {
            Intent i = new Intent(context, WishActivity.class);
            i.putExtra("Id", wishList.get(position).getId());
            i.putExtra("Title", wishList.get(position).getTitle());
            i.putExtra("Details", wishList.get(position).getDetails());
            i.putExtra("Category",wishList.get(position).getCategory());
            i.putExtra("Price",wishList.get(position).getPrice());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_item_title;
        TextView tv_item_price;
        TextView tv_item_category;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview_id);
            tv_item_title = itemView.findViewById(R.id.item_title_id);
            tv_item_price = itemView.findViewById(R.id.item_price);
            tv_item_category = itemView.findViewById(R.id.item_category);
        }
    }
}
