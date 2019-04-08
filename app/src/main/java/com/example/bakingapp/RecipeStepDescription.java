package com.example.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.fragment.RecipeDescription;
import com.example.bakingapp.model.Step;

public class RecipeStepDescription extends AppCompatActivity {

    public static final String INTENT_DATA_KEY = "step_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_description);

        if (savedInstanceState == null) {
            Step step = (Step) getIntent().getSerializableExtra(INTENT_DATA_KEY);

            RecipeDescription recipeDescription = RecipeDescription.newInstance(step);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_description_container, recipeDescription)
                    .commit();
        }

    }
}
