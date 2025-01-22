package org.automation.automate.steps.sample;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.automate.ui.pages.StackoverflowPage;
import org.testng.Assert;

public class StackOverflowSteps {
    private final StackoverflowPage stackoverflowPage;

    public StackOverflowSteps() {
        stackoverflowPage = new StackoverflowPage();
    }

    @Given("user opens stack overflow home page")
    public void userOpensStackOverflowHomePage() {
        stackoverflowPage.open();
    }

    @Then("validate login button is displayed")
    public void validateLoginButtonIsDisplayed() {
        Assert.assertTrue(stackoverflowPage.isLoginButtonDisplayed(), "Login button is not displayed");
    }

    @When("user searches for {string} in stackoverflow")
    public void userSearchesForInStackoverflow(String keyword) {
        stackoverflowPage.search(keyword);
    }

    @Then("validate header text is {string}")
    public void validateHeaderTextIs(String expectedHeader) {
        Assert.assertEquals(stackoverflowPage.getHeaderText(), expectedHeader, "Header text does not match");
    }

    @When("user completes general info section by filling all mandatory fields")
    public void userCompletesGeneralInfoSectionByFillingAllMandatoryFields(DataTable dataTable) {

    }

    @Then("validate stackoverflow closes")
    public void validateStackoverflowCloses() {
    }
}
