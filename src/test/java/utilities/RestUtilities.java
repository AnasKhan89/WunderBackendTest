package utilities;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

public class RestUtilities {

    public static Response response;
    public static RequestSpecification requestSpecification;


    public static Response getRequest(String url) {
        requestSpecification = RestAssured.with();
        response = requestSpecification.get(url);
        return response;
    }

    public static Response postRequest(String url, String body) throws Exception {
        requestSpecification = RestAssured.with();
        response = requestSpecification.contentType("application/json").body(body).post(url);
        return response;
    }




    public static Response deleteRequest(String url) throws Exception {
        requestSpecification = RestAssured.with();
        response = requestSpecification.delete(url);
        return response;
    }


}
