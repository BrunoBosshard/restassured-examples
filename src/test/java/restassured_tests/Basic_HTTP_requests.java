package restassured_tests;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic_HTTP_requests {

    // Global variable
    int id;

    @Test(priority = 1)
    void testGetUsers()
    {
        given()

        .when()
            .get("https://reqres.in/api/users?page=2")

        .then()
            .statusCode(200)
            .body("page",equalTo(2))
            .log().all();   // Logs to terminal
    }

    // Terminates after ".when()" and has no ".then()" section
    // The return value "id" is stored in the global variable
    @Test(priority = 2)
    void testCreateUser()
    {
        HashMap data = new HashMap();
        data.put("name","a_new_name");
        data.put("job","engineer");

        id=given()
            .contentType("application/json")
            .body(data)

        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");
    }

    // Reuses the id that was generated in the previous test "testCreateUser"
    // Only executes when previous test "testCreateUser" was successful
    @Test(priority = 3, dependsOnMethods = {"testCreateUser"})
    void testUpdateUser()
    {
        HashMap data = new HashMap();
        data.put("name","a_updated_name");
        data.put("job","manager");

        given()
            .contentType("application/json")
            .body(data)

        .when()
            .put("https://reqres.in/api/users/" + id)

        .then()
            .statusCode(200)
            .log().all();   // Logs to terminal
    }

    // Reuses the id that was generated in the previous test "testCreateUser"
    // Only executes when previous test "testCreateUser" was successful
    @Test(priority = 4, dependsOnMethods = {"testCreateUser"})
    void testDeleteUser()
    {
        given()

        .when()
            .delete("https://reqres.in/api/users/" + id)

        .then()
            .statusCode(204)
            .log().all();   // Logs to terminal
    }
}
