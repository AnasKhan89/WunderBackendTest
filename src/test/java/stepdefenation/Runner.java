package stepdefenation;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
                features={"src/test/resources/featurefile/Crud_Employee.feature"}
                //,tags={"~@ignore"}
                )
public class Runner {}