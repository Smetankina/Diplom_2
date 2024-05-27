package ru.praktikum.yandex.userTest;

import org.junit.Test;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

public class DeleteUserTest {


    UserClient userClient = new UserClient();
    User user = new RandomUser();




    @Test
    public void deleteUserTest(){
        userClient.registerUser(user);
         userClient.deleteUser(user).then().statusCode(202);

    }
}
