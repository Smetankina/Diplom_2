package ru.praktikum.yandex.orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
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

public class CreateOrderNegativeTest {

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
    public void tearDown(){
        try
        {userClient.deleteUser(user);}
        catch (NullPointerException e){
            System.out.println("User wasn't created. There is nothing to delete");
        }
    }

    @Test
    @Description("Похоже баг")
    @DisplayName("Похоже баг -nCreate order without token")
    public void createOrderWithoutToken() {

        orderClient.createOrderWithoutToken(order).then().body("success", equalTo(false));


    }

    @Test
    @Description("Create order with token without ingredients")
    @DisplayName("Create order with token without ingredients")
    public void checkCreateOrderWithTokenWithoutIngredientsTest() {
        userClient.registerUser(user);
        String accessToken = userClient.loginUser(user).then().extract().path("accessToken").toString().substring(7);
        Order order = new Order();
        orderClient.createOrderWithToken(order, accessToken).then().statusCode(400);


    }


    @Test
    @Description("Create order with token with invalid hash ingredients")
    @DisplayName("Create order with token with invalid hash ingredients")
    public void checkCreateOrderWithTokenWithWrongHashIngredientsTest() {
        userClient.registerUser(user);
        String accessToken = userClient.loginUser(user).then().extract().path("accessToken").toString().substring(7);

        String randomHashCode = RandomStringUtils.random(20, true, true);


        ingredients.add(0, randomHashCode);
        Order order = new Order(ingredients);
        orderClient.createOrderWithToken(order, accessToken).then().statusCode(500);


    }

}
