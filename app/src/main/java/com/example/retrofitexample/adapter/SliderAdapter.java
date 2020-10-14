package com.example.retrofitexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.MyDialogBox;
import com.example.retrofitexample.R;
import com.example.retrofitexample.SlideDialogbox;
import com.example.retrofitexample.model.Item;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<Item> list;
    Context context;

    public SliderAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        int size = 10;
        for (int i = 0; i <= size; i++) {
            return size;
        }
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slider_list, container, false);
        ImageView image = view.findViewById(R.id.image);
        final Item item = list.get(position);
        Glide.with(context).load(item.getDownloadUrl()).into(image);
        container.addView(view, 0);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogBox myDialogBox = new MyDialogBox(context, item.getDownloadUrl(),item.getAuthor(),2);
                myDialogBox.getWindow().setBackgroundDrawableResource(android.R.color.transparent);  //background transparent
                myDialogBox.show();
            }
        });
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}