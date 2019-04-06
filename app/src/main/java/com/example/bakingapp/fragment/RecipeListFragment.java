package com.example.bakingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.MainActivity;
import com.example.bakingapp.R;
import com.example.bakingapp.adapter.RecipeListAdapter;
import com.example.bakingapp.data.Data;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.ApiInterface;
import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.SimpleIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListFragment extends Fragment implements RecipeListAdapter.RecipeListItemListner {

    SimpleIdlingResource idlingResource;
    private OnRecipeItemClickListner onRecipeItemClickListner;

    public interface OnRecipeItemClickListner{
        void click(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onRecipeItemClickListner = (OnRecipeItemClickListner) context;
        }
        catch (ClassCastException ex){
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_list_fragment,container,false);
        final RecyclerView mRecyclerView = view.findViewById(R.id.rv_recipe_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        final RecipeListAdapter adapter = new RecipeListAdapter(this);
        mRecyclerView.setAdapter(adapter);

        idlingResource = (SimpleIdlingResource) ((MainActivity) getActivity()).getIdlingResource();
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        NetworkUtils.getRetrofit().create(ApiInterface.class).getDetails().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipies = response.body();
                Data.setRecipies(recipies);
                Log.i("data", "onResponse: "+ recipies);
                adapter.setRecipeData(recipies);
                idlingResource.setIdleState(true);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.i("error", "onFailure: " + t.getMessage());
                idlingResource.setIdleState(true);
            }
        });

        return view;

    }

    @Override
    public void click(Recipe recipe) {
       onRecipeItemClickListner.click(recipe.getId());
    }
}
