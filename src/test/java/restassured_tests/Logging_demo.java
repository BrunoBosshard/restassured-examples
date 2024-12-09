package restassured_tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Logging_demo {

    @Test
    void testLogs()
    {
        given()

        .when()
            .get("https://reqres.in/api/users?page=2")

        .then()
            .log().body()
            .log().cookies()
            .log().headers()
            .log().status();
            // Note: More log options are available
    }
}
