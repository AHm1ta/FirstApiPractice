package com.example.retrofitexample;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

public class SlideDialogbox extends Dialog {
    private String imageUrl;
    Context context;

    public SlideDialogbox(@NonNull Context context, String imageUrl) {
        super(context);
        this.imageUrl = imageUrl;
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.slider_popup);

        ImageView image = findViewById(R.id.image_view);

        if ( imageUrl != null && !imageUrl.isEmpty()){
            Glide.with(context).load(imageUrl).into(image);



        }

    }
}
