package com.example.bakingapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://d17h27t6h515a5.cloudfront.net/";

    static Gson gson = new GsonBuilder().create();
    static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    public static Retrofit getRetrofit() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(httpClientBuilder.build())
                    .build();
        }
        return retrofit;
    }

}
