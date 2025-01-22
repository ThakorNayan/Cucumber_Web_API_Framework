package org.automation.automate.common.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.automation.automate.common.reporting.ExtentReportManager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APIUtil {
    public RequestSpecification getRequestSpecification(String uri) {
        return RestAssured.given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation();
    }

    public RequestSpecification getRequestSpecification(String uri, Object payload, Map<String, ?> headers) {
        return RestAssured.given()
                .baseUri(uri)
                .headers(headers)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(payload);
    }

    public RequestSpecification getRequestSpecification(String uri, Object payload) {
        return RestAssured.given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(payload);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification specification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint: " + specification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method: " + specification.getMethod());
        ExtentReportManager.logInfoDetails("Request Headers");
        ExtentReportManager.logHeaders(specification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Query Parameters: " + specification.getQueryParams());
        if (specification.getBody() != null) {
            ExtentReportManager.logInfoDetails("Request Body");
            ExtentReportManager.logJson(specification.getBody());
        }
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Status: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Time(milliseconds): " + response.getTimeIn(TimeUnit.MILLISECONDS));
        ExtentReportManager.logInfoDetails("Response Headers");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        if (response.getBody().prettyPrint() != null) {
            ExtentReportManager.logInfoDetails("Response Body");
            ExtentReportManager.logJson(response.getBody().prettyPrint());
        }
    }

    private static void printApiLogsInReport(RequestSpecification requestSpecification, Response response) {
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
    }

    public Response get(String uri) {
        RequestSpecification request = getRequestSpecification(uri);
        Response response = request.get();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response get(String uri, Map<String, ?> queryParams) {
        RequestSpecification request = getRequestSpecification(uri).queryParams(queryParams);
        Response response = request.get();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response post(String uri, Object body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.post();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response post(String uri, String body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.post();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response post(String uri, Object body, Map<String, ?> queryParams) {
        RequestSpecification request = getRequestSpecification(uri, body).queryParams(queryParams);
        Response response = request.post();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response put(String uri, Object body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.put();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response put(String uri, String body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.put();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response put(String uri, Object body, Map<String, ?> queryParams) {
        RequestSpecification request = getRequestSpecification(uri, body).queryParams(queryParams);
        Response response = request.put();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response patch(String uri, Object body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.patch();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response patch(String uri, String body) {
        RequestSpecification request = getRequestSpecification(uri, body);
        Response response = request.patch();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response patch(String uri, Object body, Map<String, ?> queryParams) {
        RequestSpecification request = getRequestSpecification(uri, body).queryParams(queryParams);
        Response response = request.patch();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response delete(String uri) {
        RequestSpecification request = getRequestSpecification(uri);
        Response response = request.delete();
        printApiLogsInReport(request, response);
        return response;
    }

    public Response delete(String uri, Map<String, ?> queryParams) {
        RequestSpecification request = getRequestSpecification(uri).queryParams(queryParams);
        Response response = request.delete();
        printApiLogsInReport(request, response);
        return response;
    }

    public JsonPath getJsonPath(Response response) {
        return response.jsonPath();
    }

    public ValidatableResponse validateJsonSchema(Response response, String jsonSchemaPath) {
        return response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }
}
