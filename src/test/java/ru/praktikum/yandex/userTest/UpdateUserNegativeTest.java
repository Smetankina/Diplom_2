package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;

import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateUserNegativeTest {

    UserClient userClient = new UserClient();
    User user = new RandomUser();

    @After
    public void tearDown() {
        userClient.deleteUser(user);
    }



    @Test
    @DisplayName("Update user  without Token returns 401")
    @Description("Update user  without Token returns 401")
    public void checkUpdateUserWithoutTokenReturnStatusCode401() {
        userClient.registerUser(user);
        userClient.updateUserWithoutToken(new RandomUser()).then().statusCode(401);


    }


    @Test
    @DisplayName("Update user  without Token returns error message")
    @Description("Update user  without Token returns error message")
    public void checkUpdateUserReturnsErrorMessage() {
        userClient.registerUser(user);
        userClient.updateUserWithoutToken(new RandomUser()).then().body("message", equalTo("You should be authorised"));

    }
    @Test
    @DisplayName("Update user  without Token returns is not success")
    @Description("Update user  without Token returns is not success")
    public void checkUpdateUserReturnsIsNotSuccess() {
        userClient.registerUser(user);
        userClient.updateUserWithoutToken(new RandomUser()).then().body("success", equalTo(false));


    }

}
