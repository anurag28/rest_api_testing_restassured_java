package com.qa.client;

import com.qa.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RestAssuredClient extends TestBase {

    public Response get(String url){

        RestAssured.baseURI = url;

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET);

        return response;

    }

    public int getResponseStatusCode(Response response){
        return response.getStatusCode();
    }

    public String getResponseStatusLine(Response response){
        return response.getStatusLine();
    }

    public JSONObject getResponseBody(Response response){
        String responsBody =  response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responsBody);
        return jsonResponse;
    }

    public Response post(String url, String entityString, HashMap<String, String> headerMap){
        RestAssured.baseURI = url;

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.body(entityString);
        for (Map.Entry<String, String> header: headerMap.entrySet()) {
            httpRequest.header(header.getKey(), header.getValue());
        }

        Response response = httpRequest.request(Method.POST);
        return response;
    }
}
