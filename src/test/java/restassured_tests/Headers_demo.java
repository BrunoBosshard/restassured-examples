package restassured_tests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Headers_demo {
4
    @Test
    void testHeadersBasic()
    {
        given()

        .when()
            .get("https://www.google.com/")

        .then()
            .header("Content-Type","text/html; charset=ISO-8859-1")
            .header("Content-Encoding","gzip")
            .header("Server","gws");
    }

    @Test
    void testHeadersAll()
    {
        Response res = given()

        .when()
            .get("https://www.google.com/");

        // Get single Header info
        String header_value = res.getHeader("Content-Type");
        System.out.println("The value of Header 'Content-Type' is: " + header_value);
        System.out.println();

        // Get all Headers info
        Headers myheaders = res.getHeaders();
        for(Header h:myheaders) // Loop through all Headers
        {
            System.out.println("The Header name is '" + h.getName() + "' and the Header value is '" + h.getValue() + "'");
        }
    }
}
