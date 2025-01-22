package org.automation.automate.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.automation.automate.api.pojo.request.UserDetailsRequest;
import org.automation.automate.api.pojo.response.UserDetailsResponse;
import org.automation.automate.common.constants.Directory;
import org.automation.automate.common.utils.APIUtil;
import org.automation.automate.common.utils.CommonUtil;
import org.automation.automate.common.utils.FakerUtil;
import org.automation.automate.common.utils.PropertyUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SampleAPITest {
    APIUtil api = new APIUtil();
    String createUserJsonPath = Directory.API_TESTS_SRC_TEST_RESOURCES + "testdata/json/request/createuser.json";
    String username = FakerUtil.firstName();
    String job = FakerUtil.jobTitle();
    String updateName = FakerUtil.firstName();
    String updateJob = FakerUtil.jobTitle();
    String reqResURL = PropertyUtil.getProperty(CommonUtil.getEnvironment(), "api.url");
    String userID = "";

    @Test(priority = 0)
    public void createUser() throws IOException {
        UserDetailsRequest userDetailsRequest = new ObjectMapper().readValue(new File(createUserJsonPath), UserDetailsRequest.class);
        userDetailsRequest.setName(username);
        userDetailsRequest.setJob(job);

        Response response = api.post(reqResURL + "/api/users", userDetailsRequest);
        response.then().statusCode(201);
        System.out.println(response.asPrettyString());
        UserDetailsResponse userDetailsResponse = response.as(UserDetailsResponse.class);
        userID = userDetailsResponse.getId();
    }

    @Test(priority = 1)
    public void updateUser() throws IOException {
        UserDetailsRequest updateRequest = new ObjectMapper().readValue(new File(createUserJsonPath), UserDetailsRequest.class);
        updateRequest.setName(updateName);
        updateRequest.setJob(updateJob);
        Response response = api.put(reqResURL + "/api/users/" + userID, updateRequest);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("name"), updateName);
        Assert.assertEquals(response.jsonPath().get("job"), updateJob);

        UserDetailsResponse response1 = response.as(UserDetailsResponse.class);
        Assert.assertEquals(response1.getName(), updateName);
        Assert.assertEquals(response1.getJob(), updateJob);

    }

    @Test(priority = 2)
    public void deleteUser() {
        api.delete(reqResURL + "/api/users/" + userID)
                .then()
                .statusCode(200);
    }
}
