package stepdefenation;

import RestService.ServiceBaseUrl;
import RestService.URLEndPoints;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import sun.invoke.empty.Empty;
import utilities.RestUtilities;
import utilities.commonMethods;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class TestCase1 {

  //  RequestSpecification request;
    Response response;

    private static final Logger logger = LogManager.getLogger(TestCase1.class);

    @Given("perform a POST request to create user")
    public void postRequestToServer() throws Exception {

        String jsonFile = commonMethods.loadFile("request/createuser.json");
        response = RestUtilities.postRequestWithHeaders(ServiceBaseUrl.BASE_URI+URLEndPoints.CREATE_USER_ENDPOINT,jsonFile);
        logger.info("Response is " + response);
        System.out.println(response.getBody().asString());

    }

    @When("^verify the status '(\\d+)'$")
    public void verify_the_status(int arg1) {
        // Write code here that turns the phrase above into concrete actions
       Assert.assertEquals(response.getStatusCode(),arg1);
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
        // Write code here that turns the phrase above into concrete actions
        File proDir = new File("src");
        String id = commonMethods.readFromFile(proDir+"\\test\\resources\\request\\storevalue2.txt");
        response = RestUtilities.getRequest(ServiceBaseUrl.BASE_URI+URLEndPoints.GET_USER_ENDPOINT+id);

    }

    @When("^verify the status '<code>'$")
    public void verifyTheStatusCode(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(),arg1);
    }



    @Then("^verify user is exist with \"([^\"]*)\"$")
    public void verify_user_is_exist_with(String name) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(name,response.jsonPath().getString("employee_name"));
    }



}
