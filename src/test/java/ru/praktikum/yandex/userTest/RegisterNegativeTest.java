package ru.praktikum.yandex.userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterNegativeTest {
    private static final String EMAIL = RandomStringUtils.random(10, true, true) + "@yandex.ru";
    private static final String PASSWORD = RandomStringUtils.random(10, true, true);
    private static final String NAME = RandomStringUtils.random(10, true, false);



    UserClient userClient = new UserClient();
    User user = new RandomUser();



    @After
    public void tearDown(){
        try
        {userClient.deleteUser(user);}
        catch (NullPointerException e){
            System.out.println("User wasn't created. There is nothing to delete");
        }
    }


    @Test
    @Description("Creation of user who already exist Status Code 403")
    @DisplayName("register of the user already exist returns 403 Forbidden")
    public void registerUserReturns403Forbidden(){
        userClient.registerUser(user);
        userClient.registerUser(user).then().assertThat().statusCode(403);
    }

    @Test
    @Description("Creation of user who already exist Not Success")
    @DisplayName("register of the user already exist returns isNotSuccess")
    public void registerUserReturnsNotSuccess(){
        userClient.registerUser(user);
     Assert.assertFalse(userClient.registerUserReturnObject(user).isSuccess());
    }

    @Test
    @Description("Creation of user who already exist returns Error message")
    @DisplayName("register of the user already exist returns User already exists")
    public void registerUserReturnsBody(){
        userClient.registerUser(user);
        userClient.registerUser(user).then().body("message", equalTo("User already exists"));
    }

    @Test
    @Description("Creation of user without required fields 403 StatusCode")
    @DisplayName("register user without required fields 403 StatusCode")
    public void registerUserWithoutRequiredFieldsPasswordTest(){
        userClient.registerUser(new User(EMAIL, null, NAME)).then().statusCode(403);
    }

    @Test
    @Description("Creation of user without required fields Message")
    @DisplayName("register user without required fields check Message")
    public void registerUserWithoutEmailTest(){
        userClient.registerUser(new User("", PASSWORD, NAME)).then().body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @Description("Creation of user without required fields not success")
    @DisplayName("register user without required fields isNotSuccess")
    public void registerUserWithoutNameTest(){
        userClient.registerUser(new User(EMAIL, PASSWORD, "")).then().body("success", equalTo(false));
    }

}
