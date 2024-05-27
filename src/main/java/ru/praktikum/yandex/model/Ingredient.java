package ru.praktikum.yandex.model;

import java.util.List;

public class Ingredient {

    private String ingredient;
    private boolean isSuccess;
    private List<Data> data;



    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public Ingredient() {
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
