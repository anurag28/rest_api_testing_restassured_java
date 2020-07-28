package com.qa.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestAssuredClient;
import com.qa.data.Users;


import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class PostAPITest extends TestBase {

    TestBase testBase;
    RestAssuredClient restAssuredClient;
    String api_url, service_url, url;
    Users users;
    ObjectMapper mapper;
    Response response;


    @BeforeClass
    public  void setUp(){
        testBase = new TestBase();
        restAssuredClient = new RestAssuredClient();
        api_url = prop.getProperty("base_url");
        service_url = prop.getProperty("service_url");
        url = api_url + service_url;
        mapper = new ObjectMapper();
    }

    @Test
    public void Test01_verify_response_code() throws JsonProcessingException {
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        users = new Users("Anurag Rajvanshi", "Engineer II");
        String entityString;
        entityString = mapper.writeValueAsString(users);
        response = restAssuredClient.post(url, entityString, headerMap);
        Assert.assertEquals(restAssuredClient.getResponseStatusCode(response), STATUS_RESPONSE_CODE_201);
    }

    @Test
    public void Test02_verify_rsponse_body() throws JsonProcessingException {
        JSONObject responseString = restAssuredClient.getResponseBody(response);
        Users userCreated = mapper.readValue(responseString.toString(), Users.class);
        Assert.assertEquals(userCreated.getName(), users.getName());

    }
}
