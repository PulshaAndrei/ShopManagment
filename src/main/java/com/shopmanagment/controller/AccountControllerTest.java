package com.shopmanagment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.shopmanagment.Application;
import com.shopmanagment.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Andrei on 18.12.2016.
 */
//@RunWith(SpringRunner.class)
public class AccountControllerTest {

    private String username = "test2";
    private String password = "test2";
    private String wrongPassword = "notTest";
    private String baseURL = "http://localhost:8080/";
    private static boolean setUpIsDone = false;

    @Autowired
    private Application application;

    @Before
    public void setup() {
        if (!setUpIsDone) {
            this.application = new Application();
            String[] args = {"test"};
            application.main(args);
            setUpIsDone = true;
        }
    }

    @Test
    public void login() throws Exception {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(account);

        HttpResponse<JsonNode> response = Unirest.put(baseURL + "auth/login")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();

        assertNotNull(response.getBody().getObject().getString("data"));
    }

    @Test
    public void incorrectCredential() throws Exception {
        Account wrongAccount = new Account();
        wrongAccount.setUsername(username);
        wrongAccount.setPassword(wrongPassword);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(wrongAccount);

        HttpResponse<JsonNode> response = Unirest.put(baseURL + "auth/login")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();

        assertEquals("Incorrect username or password.", response.getBody().getObject().getString("message"));
    }

    @Test
    public void registerExistAccount() throws Exception {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(account);

        HttpResponse<JsonNode> response = Unirest.post(baseURL + "auth/signup")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();

        assertEquals("This username already exist.", response.getBody().getObject().getString("message"));
    }

}