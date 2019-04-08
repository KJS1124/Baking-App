package com.example.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.bakingapp.data.Data;
import com.example.bakingapp.fragment.RecipeDescription;
import com.example.bakingapp.fragment.RecipeStepFragment;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.Optional;

public class RecipeStepActivity extends AppCompatActivity implements RecipeStepFragment.OnRecipeStepClickListner {

    public static final String INTENT_DATA_KEY = "recipe_id";
    public static final String INTENT_DATA_MODEL_KEY = "recipe";
    private static final String INITISLIZED_LAYOUTS = "InitializedLayOut";
    private boolean isTwoPane = false;
    private int initlize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        if (savedInstanceState == null || savedInstanceState.getInt(INITISLIZED_LAYOUTS)<2) {
            initlize++;
            FragmentManager fragmentManager = getSupportFragmentManager();
            final int recipeId = getIntent().getIntExtra(INTENT_DATA_KEY, 0);
            if (findViewById(R.id.recipe_step_land) != null) {
                isTwoPane = true;
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Optional<Recipe> recipe = Data.getRecipies().stream().filter(p -> p.getId() == recipeId).findFirst();
                recipe.ifPresent(recipe1 -> {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(INTENT_DATA_MODEL_KEY, recipe1);
                    RecipeStepFragment recipeStepFragment = RecipeStepFragment.getInstance(bundle, this);
                    if (!isTwoPane)
                        fragmentManager.beginTransaction().add(R.id.recipe_step_container, recipeStepFragment).commit();
                    else {
                        fragmentManager.beginTransaction().add(R.id.lv_recipe_step, recipeStepFragment).commit();
                        RecipeDescription recipeDescription = RecipeDescription.newInstance(recipe1.getSteps().get(0));
                        fragmentManager.beginTransaction().add(R.id.fl_content, recipeDescription).commit();
                    }
                });
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INITISLIZED_LAYOUTS,initlize);
    }

    @Override
    public void onClick(Step step) {
        if (!isTwoPane) {
            Intent intent = new Intent(RecipeStepActivity.this, RecipeStepDescription.class);
            intent.putExtra(RecipeStepDescription.INTENT_DATA_KEY, step);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_content, RecipeDescription.newInstance(step)).commit();
        }
    }
}
