package com.example.bakingapp.fragment;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeStepActivity;
import com.example.bakingapp.adapter.RecipeStepAdapter;
import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;
import com.example.bakingapp.widgit.BakingAppWidgit;

@SuppressLint("ValidFragment")
public class RecipeStepFragment extends Fragment {
    private OnRecipeStepClickListner mListner;
    private Button appWidgit;
    public static final String SHARED_PREFERENCES = "pref";
    public static final String INGREDIENT_PREFERENCES = "ingredient";


    public interface OnRecipeStepClickListner {
        void onClick(Step step);
    }

    public RecipeStepFragment(){

    }

    public static RecipeStepFragment getInstance(Bundle bundle,OnRecipeStepClickListner onRecipeStepClickListner){
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment(onRecipeStepClickListner);
        recipeStepFragment.setArguments(bundle);
        return recipeStepFragment;
    }

    @SuppressLint("ValidFragment")
    public RecipeStepFragment(OnRecipeStepClickListner onRecipeStepClickListner){
        this.mListner = onRecipeStepClickListner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_step_list,container,false);
        Recipe recipe = (Recipe) getArguments().getSerializable(RecipeStepActivity.INTENT_DATA_MODEL_KEY);

        ListView listView = view.findViewById(R.id.lv_recipe_step);
        appWidgit = view.findViewById(R.id.add_to_widgit);
        TextView ingredients = view.findViewById(R.id.recipe_ingredients);

        RecipeStepAdapter adapter = new RecipeStepAdapter(getContext(),recipe.getSteps());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListner.onClick(recipe.getSteps().get(position));
            }
        });


        StringBuilder ingredientsData = new StringBuilder("Ingredients \n");
        for(Ingredient ingredient: recipe.getIngredients())
            ingredientsData.append("\t"+ ingredient.getIngredient() + "\t" + ingredient.getQuantity()+" "+ ingredient.getMeasure()+"\n");

        ingredients.setText(ingredientsData.toString());

        appWidgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity()
                        .getSharedPreferences(SHARED_PREFERENCES, container.getContext().MODE_PRIVATE);
                sharedPreferences.edit().putString(INGREDIENT_PREFERENCES,recipe.getName()+"\n"+
                        ingredientsData.toString()).apply();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
                Bundle bundle = new Bundle();
                int appWidgetId = bundle.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
                BakingAppWidgit.updateAppWidget(getActivity(), appWidgetManager, appWidgetId, recipe.getName()+"\n"+
                        ingredientsData.toString());
                Toast.makeText(getActivity(), "Added " + recipe.getName() + " to Widget.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
