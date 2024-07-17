package webTestFramework.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import webTestFramework.lib.pages.HomePage;
import webTestFramework.lib.pages.SummaryToolPage;

public class HomePageStepDefs extends StepDefsSuper {
    private HomePage homepage;
    private SummaryToolPage summaryToolPage;
    
    @Given("I am on the homepage")
    public void iAmOnTheHomePage() {
        webDriver.get("https://homepagehere.com");
        homepage = new HomePage(webDriver);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String arg0) {
        summaryToolPage = homepage.clickStartBtn();
    }

    @Then("I will be directed to the summarization page")
    public void iWillBeDirectedToTheSummarizationPage() {
        MatcherAssert.assertThat(summaryToolPage.getUrl(), Is.is("https://summaryToolPage.com"));
    }
}
