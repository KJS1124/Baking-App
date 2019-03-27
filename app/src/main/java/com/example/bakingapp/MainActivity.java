package com.example.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bakingapp.fragment.RecipeListFragment;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeItemClickListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void click(int id) {
        Intent intent = new Intent(MainActivity.this, RecipieStepActivity.class);
        intent.putExtra(RecipieStepActivity.INTENT_DATA_KEY,id);
        startActivity(intent);
    }
}
