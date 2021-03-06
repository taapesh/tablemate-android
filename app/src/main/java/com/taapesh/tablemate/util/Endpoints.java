package com.taapesh.tablemate.util;

import okhttp3.HttpUrl;


public class Endpoints {
    private static final HttpUrl BASE = HttpUrl.parse("http://safe-springs-46272.herokuapp.com/");

    public static final HttpUrl TABLE_ENDPOINT = HttpUrl.parse(BASE + "get_table/");
    public static final HttpUrl SERVER_ID_ENDPOINT = HttpUrl.parse(BASE + "get_server_id/");
    public static final HttpUrl TABLE_REQUEST_ENDPOINT = HttpUrl.parse(BASE + "request_service/");
    public static final HttpUrl FINISH_ENDPOINT = HttpUrl.parse(BASE + "finish_and_pay");
    public static final HttpUrl CREATE_TABLE_ENDPOINT = HttpUrl.parse(BASE + "create_table/");
    public static final HttpUrl SERVER_TABLES_ENDPOINT = HttpUrl.parse(BASE + "server_tables/");
    public static final HttpUrl LOGIN_ENDPOINT = HttpUrl.parse(BASE + "login/");
    public static final HttpUrl REGISTER_ENDPOINT = HttpUrl.parse(BASE + "register/");

    // Test for create or join table
    public static final String TEST_ADDRESS = "1234 Restaurant St.";
    public static final String TEST_RESTAURANT_NAME = "Awesome Restaurant";
    public static final String TEST_TABLE_NUM = "1";
    public static final String TEST_TABLE_ADDR_COMBO = "1_1234 Restaurant St.";

    // Test for login
    public static final String TEST_EMAIL = "test@gmail.com";
    public static final String TEST_PASSWORD = "12345";

    // Test for register
    public static final String TEST_EMAIL_REGISTER = "test@gmail.com";
    public static final String TEST_PASSWORD_REGISTER = "12345";
    public static final String TEST_FIRST_NAME = "John";
    public static final String TEST_LAST_NAME = "Doe";
}
