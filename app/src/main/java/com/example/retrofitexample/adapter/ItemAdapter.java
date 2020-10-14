package com.example.retrofitexample.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.MyDialogBox;
import com.example.retrofitexample.R;
import com.example.retrofitexample.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> list;
    Context context;

    public ItemAdapter(List<Item> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_collection_list,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item=list.get(position);
        //Picasso.with(context).load(item.getUrl()).into(holder.image);
        Glide.with(context).load(item.getDownloadUrl()).into(holder.image);
        Log.d("url", item.getDownloadUrl());
        holder.author.setText(item.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogBox myDialogBox = new MyDialogBox(context, item.getDownloadUrl(),item.getAuthor(), 1);
                myDialogBox.getWindow().setBackgroundDrawableResource(android.R.color.transparent);  //background transparent
                myDialogBox.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        ImageView image;
        LinearLayout item_list;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            author=itemView.findViewById(R.id.itemName);
            image=itemView.findViewById(R.id.image);
            item_list=itemView.findViewById(R.id.image_id);
        }
    }
}
