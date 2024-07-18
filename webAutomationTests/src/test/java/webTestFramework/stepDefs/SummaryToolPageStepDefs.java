package webTestFramework.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import webTestFramework.lib.pages.SummaryToolPage;

import static org.hamcrest.Matchers.notNullValue;

public class SummaryToolPageStepDefs extends StepDefsSuper{
    private SummaryToolPage summaryToolPage;

    @Given("I am on the summary tool page")
    public void iAmOnTheSummaryToolPage() {
        webDriver.get("https://summaryToolPage.com");
        summaryToolPage = new SummaryToolPage(webDriver);
    }

    @When("I click the upload icon")
    public void iClickTheUploadIcon() {
        summaryToolPage.clickUploadIcon();
    }

    @And("I click on a document to load")
    public void iClickOnADocumentToLoad() {
    }

    @And("I click the Summarise button")
    public void iClickTheSummariseButton() {
        summaryToolPage.clickSummariseButton();
    }

    @Then("I should see a summary of the article displayed on the page")
    public void iShouldSeeASummaryOfTheArticleDisplayedOnThePage() {
        MatcherAssert.assertThat(summaryToolPage.getSummary(), Is.is(notNullValue()));
    }

}
