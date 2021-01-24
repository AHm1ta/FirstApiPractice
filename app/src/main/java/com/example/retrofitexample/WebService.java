package com.example.retrofitexample;

import com.example.retrofitexample.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WebService {
    String BASE_URL="https://picsum.photos/v2/";
    String PART="list";

        Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(PART)
    Call<List<Item>> getItems();


}
