package org.automation.automate.steps.sample;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.automation.automate.ui.pages.GoogleHomePage;
import org.junit.Assert;

public class GoogleHomePageSteps {
    private final GoogleHomePage googleHomePage;

    public GoogleHomePageSteps() {
        googleHomePage = new GoogleHomePage();
    }

    @Given("a user opens google home")
    public void a_user_opens_google_home() {
        googleHomePage.open();
    }

    @And("user searches for {string}")
    public void user_searches_for(String keyword) {
        googleHomePage.searchFor(keyword);
        Assert.fail();
    }

    @Given("test user logs into Google")
    public void testUserLogsIntoGoogle() {
        googleHomePage.testUserLogin();
    }

    @Given("supervisor logs into Google")
    public void supervisorLogsIntoGoogle() {
        googleHomePage.supervisorLogin();
    }
}
