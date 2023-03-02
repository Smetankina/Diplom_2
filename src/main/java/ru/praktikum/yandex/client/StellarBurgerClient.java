package ru.praktikum.yandex.client;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StellarBurgerClient {


    private final String baseURI = "https://stellarburgers.nomoreparties.site";


    protected RequestSpecification baseSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .build();
        }

    protected RequestSpecification baseSpec(String accessToken){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .addHeader("Autorization", accessToken)
                .build();
    }
    }




