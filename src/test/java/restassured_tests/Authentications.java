package restassured_tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

    @Test(priority = 1)
    void testBasicAuthentication() {
        given()
            .auth().basic("postman","password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
   }

    @Test(priority = 2)
    void testDigestAuthentication() {
        given()
            .auth().digest("postman","password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
    }

    @Test(priority = 3)
    void testPreemptiveAuthentication() {
        given()
            .auth().preemptive().basic("postman","password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
    }

    // Requires a GitHub Personal Access Token, which can be used for both bearer and OAuth2 authentication
    @Test(priority = 1)
    void testBearerTokenAuthentication() {
        String github_personal_access_token = "REPLACE_THIS_WITH_YOUR_GITHUB_PERSONAL_ACCESS_TOKEN";

        given()
            .headers("Authorization","Bearer " + github_personal_access_token)

        .when()
            .get("https://api.github.com/user/repos")

        .then()
            .statusCode(200)
            .log().all();
    }

    // This is just the test outline, please fill in the details and the URL
    //@Test
    void testOAuth1Authentication()
    {
        given()
            .auth().oauth("consumerKey","consumerSecret","accessToken","secretToken")

        .when()
            .get("https://xxxxxxxxxx")

        .then()
            .statusCode(200)
            .log().all();
    }

    // Requires a GitHub Personal Access Token, which can be used for both bearer and OAuth2 authentication
    @Test(priority = 2)
    void testOAuth2Authentication()
    {
        String github_personal_access_token = "REPLACE_THIS_WITH_YOUR_GITHUB_PERSONAL_ACCESS_TOKEN";

        given()
            .auth().oauth2(github_personal_access_token)

        .when()
            .get("https://api.github.com/user/repos")

        .then()
            .statusCode(200)
            .log().all();
    }

    // Note: You might have to get your own OpenWeatherMap "appid" (API key)
    @Test
    void testAPIKeyAuthentication()
    {
        given()
            .pathParam("mypath","data/2.5/forecast/daily")
            .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c") // "appid" is API key
            .queryParam("q","London")
            .queryParam("units","metric")
            .queryParam("cnt","7")

        .when()
            .get("https://api.openweathermap.org/{mypath}")

        .then()
            .statusCode(200)
            .log().all();
    }
}
