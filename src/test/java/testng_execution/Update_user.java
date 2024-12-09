package testng_execution;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Update_user {

    @Test()
    void testUpdateUser(ITestContext context)
    {
        // The scope of this context variable "bearer_token" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "bearer_token" to the "Test" level
        String bearer_token = (String) context.getSuite().getAttribute("bearer_token");

        // The scope of this context variable "user_id" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "user_id" to the "Test" level
        int id = (Integer) context.getSuite().getAttribute("user_id");

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","female");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

       given()
           .headers("Authorization","Bearer " + bearer_token)
           .contentType("application/json")
           .pathParam("id",id)
           .body(data.toString())

       .when()
           .put("https://gorest.co.in/public/v2/users/{id}")

        .then()
           .statusCode(200)
           .log().all();
    }
}
