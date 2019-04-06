package com.example.bakingapp.model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    int id;
    private String name;
    private String image;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    public Recipe(String name, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe(int id, String name, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
