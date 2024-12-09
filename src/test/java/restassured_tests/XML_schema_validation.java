package restassured_tests;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class XML_schema_validation {

    @Test
    void testXmlSchemaValidation()
    {
        given()

        .when()
            .get("https://mocktarget.apigee.net/xml")

        .then()
            .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("apigeeXmlSchema.xsd"));
    }
}
