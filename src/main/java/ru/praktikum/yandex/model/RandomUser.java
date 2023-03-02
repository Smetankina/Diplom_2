package ru.praktikum.yandex.model;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUser extends User {

    private static final String EMAIL = RandomStringUtils.random(10, true, true) + "@yandex.ru";
    private static final String PASSWORD = RandomStringUtils.random(10, true, true);
    private static final String NAME = RandomStringUtils.random(10, true, false);


    public RandomUser() {
        super(EMAIL, PASSWORD, NAME);
    }


    }


