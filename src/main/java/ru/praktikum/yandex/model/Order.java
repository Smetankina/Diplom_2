package ru.praktikum.yandex.model;

import ru.praktikum.yandex.client.OrderClient;

import java.util.List;

public class Order {
    private List<String> ingredients;


    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Order() {
    }

    private OrderClient orderClient;


    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }



}
