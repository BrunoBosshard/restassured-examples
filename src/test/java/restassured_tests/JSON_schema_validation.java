package restassured_tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class JSON_schema_validation {

    @Test
    void testJsonSchemaValidation()
    {
        given()

        .when()
            .get("http://localhost:3000/store")

        .then()
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
    }
}
