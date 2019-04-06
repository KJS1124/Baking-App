package com.example.bakingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.fragment.RecipeListFragment;
import com.example.bakingapp.utils.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeItemClickListner {
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdlingResource();
    }

    @Override
    public void click(int id) {
        Intent intent = new Intent(MainActivity.this, RecipeStepActivity.class);
        intent.putExtra(RecipeStepActivity.INTENT_DATA_KEY,id);
        startActivity(intent);
    }
}
