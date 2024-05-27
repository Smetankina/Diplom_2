package ru.praktikum.yandex.client;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.yandex.model.Ingredient;
import ru.praktikum.yandex.model.Order;
import ru.praktikum.yandex.model.User;

import static io.restassured.RestAssured.given;

public class OrderClient extends StellarBurgerClient {
UserClient userClient;
User user;


    @Step
    @Description("Get ingredients as Data.class /api/ingredients")
    public Ingredient getIngredientsClass() {
        return
                given().spec(baseSpec())
                        .get("/api/ingredients")
                        .body()
                        .as(Ingredient.class);
    }

    @Step
    @Description("Get ingredients as Data.class /api/ingredients")
    public Response getIngredients(){

       return given().spec(baseSpec())
                .get("/api/ingredients");

    }



    @Step
    @Description("Create order /api/orders")
    public Response createOrderWithToken(Order order, String accessToken) {

        return given().spec(baseSpec(accessToken))
                .auth().oauth2(accessToken)
                .body(order)
                .when()
                .post("/api/orders");

    }

    @Step
    @Description("Create order /api/orders")
    public Response createOrderWithoutToken(Order order) {

        return given().spec(baseSpec())
                .body(order)
                .when()
                .post("/api/orders");

    }

    @Step
    @Description("Get order /api/orders")
    public Response getOrderWithToken(String accessToken) {

        return given().spec(baseSpec(accessToken))
                .auth().oauth2(accessToken)
                .get("/api/orders");

    }

    @Step
    @Description("Get order /api/orders")
    public Response getOrderWithoutToken() {

        return given().spec(baseSpec())
                 .get("/api/orders");

    }


}
