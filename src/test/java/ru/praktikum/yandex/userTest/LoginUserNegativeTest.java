package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginUserNegativeTest {

    UserClient userClient = new UserClient();
    User user = new RandomUser();


    @After
    public void tearDown() {
        if (user.isSuccess()){
        userClient.deleteUser(user);}
    }

    @Test
    @Description("login with invalid login and password returns  401 Status Code")
    @DisplayName("login with invalid login and password returns  401 Status Code")
    public void loginUserIncorrectRequiredFields401StatusCodeTest() {

        userClient.loginUser(user).then().assertThat().statusCode(401);
    }


    @Test
    @Description("login with invalid login and password returns error message")
    @DisplayName("login with invalid login and password returns error message")
    public void loginUserIncorrectRequiredFieldsMessageTest() {
        userClient.loginUser(user).then().body("message", equalTo("email or password are incorrect"));
    }



}
