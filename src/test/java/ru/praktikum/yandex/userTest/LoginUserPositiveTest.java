package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserPositiveTest {

    UserClient userClient = new UserClient();
    User user = new RandomUser();



    @After
    public void tearDown() {
        userClient.deleteUser(user);
    }


    @Test
    @Description("success login returns 200 OK")
    @DisplayName("success login returns 200 OK")
    public void loginUserWithToken200Test() {
        userClient.registerUser(user);
        userClient.loginUser(user)
                .then().assertThat().statusCode(200);
    }

    @Test
    @Description("success login returns message")
    @DisplayName("success login returns message")
    public void loginUserWithTokenTest() {
        userClient.registerUser(user);
        userClient.loginUser(user).then().body("success",equalTo(true));

    }


}
