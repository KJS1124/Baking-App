package com.example.bakingapp.data;

import android.util.Log;
import android.widget.Toast;

import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.ApiInterface;
import com.example.bakingapp.utils.JsonUtils;
import com.example.bakingapp.utils.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data {
    private static List<Recipe> recipies;

    public static List<Recipe> getRecipies(){
        return recipies;
    }

    public static void setRecipies(List<Recipe> recipies) {
        Data.recipies = recipies;
    }
}
