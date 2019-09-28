package stepdefenation;

import RestService.ServiceBaseUrl;
import RestService.URLEndPoints;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import utilities.RestUtilities;
import utilities.commonMethods;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class TestCase1 {

  //  RequestSpecification request;
    Response response;

    private static final Logger logger = LogManager.getLogger(TestCase1.class);

    @Given("perform a POST request to create user")
    public void postRequestToServer() throws Exception {

        String jsonFile = commonMethods.loadFile("request/createuser.json");
        response = RestUtilities.postRequest(ServiceBaseUrl.BASE_URI+URLEndPoints.CREATE_USER_ENDPOINT,jsonFile);
        logger.info("Response is " + response);
        System.out.println(response.getBody().asString());

    }

    @When("^verify the status '(\\d+)'$")
    public void verify_the_status(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @Then("^verify user is created with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_user_is_created_with_and(String name,String salary,String age) throws IOException {

        String id = response.jsonPath().get("id");
        File proDir = new File("src");
        commonMethods.writeOnFile(proDir+"\\test\\resources\\request\\storevalue2.txt",id);
        System.out.println("id is "+id);
        Assert.assertEquals(name,response.jsonPath().getString("name"));
        Assert.assertEquals(salary,response.jsonPath().getString("salary"));
        Assert.assertEquals(age,response.jsonPath().getString("age"));
    }

    @Given("^perfom a get request to get user by id$")
    public void perfomAGetRequestToGetUserById() throws Throwable {

        File proDir = new File("src");
        String id = commonMethods.readFromFile(proDir+"\\test\\resources\\request\\storevalue2.txt");
        response = RestUtilities.getRequest(ServiceBaseUrl.BASE_URI+URLEndPoints.GET_USER_ENDPOINT+id);

    }


    @Then("^verify user is exist with \"([^\"]*)\"$")
    public void verify_user_is_exist_with(String name) {

        Assert.assertEquals(name,response.jsonPath().getString("employee_name"));
    }

    @Given("^perfom a Delete request by giving user id$")
    public void perfom_a_Delete_request_by_giving_user_id() throws Exception {

        File proDir = new File("src");
        String id = commonMethods.readFromFile(proDir+"\\test\\resources\\request\\storevalue2.txt");
        response = RestUtilities.deleteRequest(ServiceBaseUrl.BASE_URI+URLEndPoints.DELETE_USER_ENDPOINT+id);

    }


    @Then("^verify that user is successfully deleted and get \"([^\"]*)\" in response$")
    public void verify_that_user_is_successfully_deleted_and_get_in_response(String message) {

        Assert.assertTrue(response.getBody().asString().contains(message));
    }


}
