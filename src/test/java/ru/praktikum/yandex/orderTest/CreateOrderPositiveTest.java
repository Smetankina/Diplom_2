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

public class CreateOrderPositiveTest {

    OrderClient orderClient = new OrderClient();
    List<String> ingredients = new ArrayList<String>();
    Order order = new Order(ingredients);

    UserClient userClient = new UserClient();
    User user = new RandomUser();


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
    public void deleteTestData(){
        userClient.deleteUser(user);
    }

    @Test
    @Description
    @DisplayName("Create order with token")
    public void createOrderWithToken() {
        userClient.registerUser(user);
        String accessToken = userClient.loginUser(user).then().extract().path("accessToken").toString().substring(7);
        orderClient.createOrderWithToken(order, accessToken).then().body("success", equalTo(true));


    }

    @Test
    @Description
    @DisplayName("Create order with token")
    public void checkCreateOrderWithTokenStatusCode() {
        userClient.registerUser(user);
        String accessToken = userClient.loginUser(user).then().extract().path("accessToken").toString().substring(7);
        orderClient.createOrderWithToken(order, accessToken).then().statusCode(200);


    }


}
