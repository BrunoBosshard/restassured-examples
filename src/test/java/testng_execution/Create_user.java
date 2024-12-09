package testng_execution;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Create_user {

    @Test()
    void testCreateUser(ITestContext context)
    {
        String bearer_token = "REPLACE_THIS_WITH_YOUR_GOREST_ACCESS_TOKEN";
        // The scope of this context variable "bearer_token" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "bearer_token" to the "Test" level
        context.getSuite().setAttribute("bearer_token",bearer_token);

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        int id = given()
            .headers("Authorization","Bearer " + bearer_token)
            .contentType("application/json")
            .body(data.toString())

        .when()
            .post("https://gorest.co.in/public/v2/users")
            .jsonPath().getInt("id");

        System.out.println("The ID of the new user is : " + id);

        // The scope of this context variable "user_id" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "user_id" to the "Test" level
        context.getSuite().setAttribute("user_id",id);
    }
}
