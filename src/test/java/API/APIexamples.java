package API;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class APIexamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5NjkyNTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxMjQ1OCwidXNlcklkIjoiNTIxOCJ9.uI2upFWnlrD9qAKx_NhRE57cEgs8GlKR3JGJWAnMBek";
    static String employee_id;

    @Test
    public void createANEmployee () {

        //prepare the request
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Elvira\",\n" +
                        "  \"emp_lastname\": \"Bissengaliyeva\",\n" +
                        "  \"emp_middle_name\": \"EB\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2023-05-20\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");

        // acts as a send key
        // prepared request --> attached all the data for the request
        Response response = requestSpecification.when().post("/createEmployee.php");
        response.prettyPrint();

        // verification of the status
        response.then().assertThat().statusCode(201);

        // verify the body of the data
        //
        //
        //
        String expectedValue = "Employee Created";

        // get the actual value out of the response  ---> this is the only task that's tough
        String actualValue = response.jsonPath().getString("Message");
        System.out.println(actualValue);

        // verify
        Assert.assertEquals(actualValue, expectedValue);

        // verify that emp_job_title is SDET.
        //
        //
        String expected = "SDET";
        String actual = response.jsonPath().getString("Employee.emp_job_title");
        Assert.assertEquals(expected, actual);
        // or
        //       response.then().assertThat().body("Employee.emp_job_title",equalTo("SDET"));
    }
}