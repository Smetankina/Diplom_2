package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import java.util.HashMap;


public class UpdateUserPositiveTest {

    UserClient userClient = new UserClient();
    User user = new RandomUser();

    @After
    public void tearDown() {
        userClient.deleteUser(user);
    }


    @Test
    @DisplayName("Update user data with Token returns 200")
    @Description("Update user data with Token returns 200")
    public void checkUpdateUserReturnStatusCode200() {
        userClient.registerUser(user);
        userClient.updateUser(new RandomUser()).then().statusCode(200);


    }


    @Test
    @DisplayName("Update user data with Token returns new Name")
    @Description("Update user data with Token returns new Name")
    public void checkUpdateUserReturnsNewName() {
        userClient.registerUser(user);
        user = new RandomUser();
        Response response = userClient.updateUser(user);
        HashMap userUpdated = response.then().extract().path("user");
        Assert.assertEquals(user.getName(), userUpdated.get("name"));

    }
    @Test
    @DisplayName("Update user data with Token returns new mail")
    @Description("похоже на баг, новый эмаил с toLowercase")
    public void checkUpdateUserReturnsNewEmail() {
        userClient.registerUser(user);
        user = new RandomUser();
        Response response = userClient.updateUser(user);
        HashMap userUpdated = response.then().extract().path("user");
        Assert.assertEquals(user.getEmail(), userUpdated.get("email"));

    }

}
