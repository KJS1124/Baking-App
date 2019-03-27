package com.example.bakingapp.model;

class Ingredient {
    int id;
    String ingredient;
    String measure;
    double quantity;

    public Ingredient(String ingredient, String measure, double quantity) {
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    public Ingredient(int id, String ingredient, String measure, double quantity) {
        this.id = id;
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredient='" + ingredient + '\'' +
                ", measure='" + measure + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
