package ru.praktikum.yandex.client;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum.yandex.model.User;
import static io.restassured.RestAssured.given;

public class UserClient extends StellarBurgerClient {


    @Step
    @Description
    @DisplayName("Base url for register")

    public Response registerUser(User user) {
        return given().spec(baseSpec())
                .body(user)
                .when()
                .post("/api/auth/register");


    }


    @Step
    @Description
    @DisplayName("Return response register user as Object")
    public User registerUserReturnObject(User user) {
        return
                given().spec(baseSpec())
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .body()
                        .as(User.class);

    }


    @Step
    @Description
    @DisplayName("Base url Login user POST /api/auth/login")

    public Response loginUser(User user) {
        return given().spec(baseSpec())
                .body(user)
                .when()
                .post("/api/auth/login");
    }


    @Step
    @Description("Base url for delete user /api/auth/user")
    public Response deleteUser(User user) {
        String accessToken = loginUser(user).then().extract().path("accessToken").toString().substring(7);

        return given().spec(baseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .when()
                .delete("/api/auth/user");
    }


    @Step
    @Description("Изменение данных пользователя")
    public Response updateUser(User user) {
        String accessToken = loginUser(user).then().extract().path("accessToken").toString().substring(7);

        return given().spec(baseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .when()
                .patch("/api/auth/user");

    }

    @Step
    @Description("Изменение данных пользователя")
    public Response updateUserWithoutToken(User user) {
        return given().spec(baseSpec())

                .body(user)
                .when()
                .patch("/api/auth/user");

    }
/*

СПРОСИТЬ!!! ПОЧЕМУ НЕ РАБОТАЕТ
    @Step
    @Description("Logout Base url /api/auth/logout")
    public Response logoutUser(User user){
        String refreshToken = loginUser(user).then().extract().path("refreshToken").toString();
        Gson gson = new Gson();
        String jsonString = "{\"token\": \""+ refreshToken+"\"}";


        return given().spec(baseSpec())
                .body(gson.toJson(jsonString))
                .when()
                .post("/api/auth/logout");
    }*/



}
