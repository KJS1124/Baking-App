package com.example.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private List<Recipe> recipes;
    private RecipeListItemListner mRecipeListClickListner;

    public interface RecipeListItemListner {
        void click(Recipe recipe);
    }

    public RecipeListAdapter(RecipeListItemListner mRecipeListClickListner) {
        this.mRecipeListClickListner = mRecipeListClickListner;
    }

    public void setRecipeData(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recipie_list_item, viewGroup, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.bind(recipes.get(i));
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mRecipeImage;
        TextView mRecipeName;
        ConstraintLayout layout;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecipeImage = itemView.findViewById(R.id.iv_recipe_list_item);
            mRecipeName = itemView.findViewById(R.id.tv_recipe_list_item);
            layout = itemView.findViewById(R.id.recipe_list_item);

            layout.setOnClickListener(this);
        }

        public void bind(Recipe recipe) {
            mRecipeName.setText(recipe.getName());
            if(recipe.getImage().length()>5)
            Picasso.get().load(String.valueOf(recipe.getImage()))
                    .error(R.color.colorAccent)
                    .placeholder(R.color.colorAccent)
                    .into(mRecipeImage);
        }

        @Override
        public void onClick(View v) {
            mRecipeListClickListner.click(recipes.get(getAdapterPosition()));
        }
    }
}
