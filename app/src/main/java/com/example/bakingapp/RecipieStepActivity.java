package com.example.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipieStepActivity extends AppCompatActivity {

    public static final String INTENT_DATA_KEY = "recipe_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_step);
    }
}
