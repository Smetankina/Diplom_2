package ru.praktikum.yandex.orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.yandex.client.OrderClient;
import ru.praktikum.yandex.client.UserClient;
import ru.praktikum.yandex.model.Data;
import ru.praktikum.yandex.model.Order;
import ru.praktikum.yandex.model.RandomUser;
import ru.praktikum.yandex.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetOrderPositiveTest {

    OrderClient orderClient = new OrderClient();
    UserClient userClient = new UserClient();
    User user = new RandomUser();
    List<String> ingredients = new ArrayList<>();
    Order order = new Order(ingredients);


    @Before
    public void generateRandomIngredientList() {
        int n = (int) (Math.random() * 14);
        List<Data> data;
        data = orderClient.getIngredientsClass().getData();

        for (int a = 1; a < n; a++) {
            int i = (int) (Math.random() * 14);
            String ingredient = data.get(i).get_id();
            ingredients.add(ingredient);
        }

    }

    @After
    public void deleteTestData() {
        userClient.deleteUser(user);
    }


    @Test
    @Description("Get order of user with Token")
    @DisplayName("Get order of user with Token")
    public void checkGetOrderUserWithToken() {
        userClient.registerUser(user);
        String accessToken = userClient.loginUser(user).then().extract().path("accessToken").toString().substring(7);
        orderClient.createOrderWithToken(order, accessToken);
        orderClient.getOrderWithToken(accessToken).then().body("success", equalTo(true));
    }


}
