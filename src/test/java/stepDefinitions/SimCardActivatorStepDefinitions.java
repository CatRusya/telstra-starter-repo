package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.model.dto.SimCardRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private SimCardRequest simCardRequest;

    @Given("a successful SIM card")
    public void aSuccessfulSimCard(){
        simCardRequest = new SimCardRequest("1255789453849037777", "successful.case@gmail.com", false);
    }

    @Given("a failed SIM card")
    public void aFailedSimCard(){
        simCardRequest = new SimCardRequest("8944500102198304826", "failed.case@gmail.com", false);
    }

    @When("a request to activate the sim card is submitted")
    public void aRequestToActivateTheSimCardIsSubmitted() {
        this.restTemplate.postForObject("http://localhost:8080/activate-sim", simCardRequest, String.class);
    }

    @Then("the activation should be successful and its state should be recorded to the database")
    public void theSimCardIsActivatedAndItsStateIsRecordedToTheDatabase() {
        var simCardRequest = this.restTemplate.getForObject("http://localhost:8080/sim-card?simCardId={simCardId}", SimCardRequest.class, 1);
        assertTrue(simCardRequest.isSuccess());
    }

    @Then("the activation should fail and its state should be recorded to the database")
    public void theSimCardFailsToActivateAndItsStateIsRecordedToTheDatabase() {
        var simCardRequest = this.restTemplate.getForObject("http://localhost:8080/sim-card?simCardId={simCardId}", SimCardRequest.class, 2);
        assertFalse(simCardRequest.isSuccess());
    }
}