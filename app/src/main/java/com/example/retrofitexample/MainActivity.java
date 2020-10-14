package com.example.retrofitexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.retrofitexample.adapter.ItemAdapter;
import com.example.retrofitexample.adapter.SliderAdapter;
import com.example.retrofitexample.model.Item;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WebService mWebService;
    ViewPager pager;
    SliderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,3000);

        pager=findViewById(R.id.pager);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        mWebService = WebService.retrofit.create(WebService.class);
        Call<List<Item>> call = mWebService.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()){
                    showItem(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showItem(List<Item> response) {
        ItemAdapter itemAdapter = new ItemAdapter(response, MainActivity.this);
        recyclerView.setAdapter(itemAdapter);

        adapter= new SliderAdapter(response, MainActivity.this);
        pager.setPadding(200,0,200,0);
        pager.setAdapter(adapter);

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (pager.getCurrentItem() < adapter.getCount() - 1) {
                        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
                    } else {
                        pager.setCurrentItem(0, true);
                    }

                }
            });
        }
    }


}