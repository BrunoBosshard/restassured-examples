package restassured_tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Cookies_demo {

    @Test
    void testCookiesInfo()
    {
        Response res = given()

        .when()
            .get("https://www.google.com/");

        // Get single Cookie info
        String cookie_value = res.getCookie("AEC");
        System.out.println("The value of Cookie AEC is: " + cookie_value);
        System.out.println();

        // Get all Cookies info
        Map<String,String> cookies_values = res.getCookies();
        for(String k:cookies_values.keySet())
        {
            System.out.println("The value of Cookie '" + k + "' is '" + res.getCookie(k) + "'");
        }
    }
}
