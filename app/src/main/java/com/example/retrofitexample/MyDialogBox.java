package com.example.retrofitexample;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

public class MyDialogBox extends Dialog {

    private String imageUrl;
    private String title;
    Context context;
    private int FLAG;

    public MyDialogBox(@NonNull Context context, String imageUrl, String title, int FLAG) {
        super(context);
        this.imageUrl = imageUrl;
        this.title=title;
        this.context=context;
        this.FLAG = FLAG;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (FLAG == 2){
            setContentView(R.layout.slider_popup);
            ImageView imageView = findViewById(R.id.image_view);
            if ( imageUrl != null && !imageUrl.isEmpty()){
                Glide.with(context).load(imageUrl).into(imageView);
            }
        }else {
            setContentView(R.layout.image_popup);
            ImageView imageView = findViewById(R.id.image_view);
            TextView title_view=findViewById(R.id.title_view);

            if ( imageUrl != null && !imageUrl.isEmpty() && !title.isEmpty()){
                Glide.with(context).load(imageUrl).into(imageView);
                title_view.setText(title);
            }
        }

    }
}
