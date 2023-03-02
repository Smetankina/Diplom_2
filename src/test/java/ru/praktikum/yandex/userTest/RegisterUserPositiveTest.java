package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import static org.hamcrest.Matchers.notNullValue;

public class RegisterUserPositiveTest {


    UserClient userClient = new UserClient();
    User user = new RandomUser();


    @Test
    @Description("Creation of unique user returns 200OK")
    @DisplayName("Successful register of the user returns 200 OK")
    public void registerUserReturns200OK() {

        userClient.registerUser(user)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @Description("Creation of unique user returns Token")
    @DisplayName("Successful register of the user returns accessToken")
    public void registerUserReturnsAccessTokenTest() {

     userClient.registerUser(user).then().body("accessToken", notNullValue());
        System.out.println(userClient.registerUser(user).body().asString());

    }


    @After
    public void tearDown() {
        userClient.deleteUser(user);

    }



}


