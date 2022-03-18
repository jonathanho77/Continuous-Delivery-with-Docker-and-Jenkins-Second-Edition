package acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/** Steps definitions for calculator.feature */
public class StepDefinitions {
    private String server = System.getProperty("calculator.url");

    private RestTemplate restTemplate = new RestTemplate();

    private String a;
    private String b;
    private String quotient;

    @Given("^I have dividend and divisor: (.*) and (.*)$")
    public void i_have_two_numbers(String a, String b) throws Throwable {
        this.a = a;
        this.b = b;
    }

    @When("^the calculator divides them$")
    public void the_calculator_divs_them() throws Throwable {
        String url = String.format("%s/div?a=%s&b=%s", server, a, b);
        quotient = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as a quotient$")
    public void i_receive_as_a_result(String expectedQuotient) throws Throwable {
        assertEquals(expectedQuotient, quotient);
    }
}
