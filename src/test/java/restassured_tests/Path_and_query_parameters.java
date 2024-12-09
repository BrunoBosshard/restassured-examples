package restassured_tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Path_and_query_parameters {

    // Example for the URL https://reqres.in/api/users?page=2&id=5
    @Test
    void testQueryAndPathParameters()
    {
        given()
            .pathParam("endpoint","users")
            .queryParam("page",2)
            .queryParam("id",5)

        .when()
            .get("https://reqres.in/api/{endpoint}") // (queryParam will be added automatically)

        .then()
            .statusCode(200)
            .log().all();
    }
}
