package testng_execution;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Delete_user {

    @Test
    void testDeleteUser(ITestContext context) {
        // The scope of this context variable "bearer_token" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "bearer_token" to the "Test" level
        String bearer_token = (String) context.getSuite().getAttribute("bearer_token");

        // The scope of this context variable "user_id" is set to the "Suite" level
        // Remove ".getSuite()" to set the scope of this context variable "user_id" to the "Test" level
        int id = (Integer) context.getSuite().getAttribute("user_id");

        given()
            .headers("Authorization", "Bearer " + bearer_token)
            .pathParam("id", id)

        .when()
            .delete("https://gorest.co.in/public/v2/users/{id}}")

        .then()
            .statusCode(204)
            .log().all();
    }
}
