package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestAssuredClient;
import com.qa.util.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetAPITestWithoutHeader extends TestBase {
    TestBase testBase;
    RestAssuredClient restAssuredClient;
    String base_url;
    String service_url;
    String url;
    Response response;

    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restAssuredClient = new RestAssuredClient();
        base_url = prop.getProperty("base_url");
        service_url = prop.getProperty("service_url");
        url = base_url + service_url;
    }

    @Test
    public void Test01_verify_status_code(){
        response = restAssuredClient.get(url);
        int statusCode = restAssuredClient.getResponseStatusCode(response);
        Assert.assertEquals(statusCode, STATUS_RESPONSE_CODE_200, "Status code is incorrect");
    }

    @Test
    public void Test02_verify_status_line(){
        String statusLine = restAssuredClient.getResponseStatusLine(response);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void Test03_verify_response_body(){
        String id = TestUtil.getValueByJPath(restAssuredClient.getResponseBody(response), "/data/id");
        Assert.assertEquals(id, "2");
    }
}
