package restassured_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Parsing_JSON_response_data {

    // Basic approach
    @Test
    void testJSONresponseBasic()
    {
        given()
            .contentType(ContentType.JSON)

        .when()
            .get("http://localhost:3000/store")

        .then()
            .statusCode(200)
            .header("Content-Type","application/json")
            .body("book[3].title",equalTo("The Lord of the Rings"));
    }

    // Approach with more possibilities
    @Test
    void testJSONresponseAdvanced()
    {
        Response res = given()
            .contentType(ContentType.JSON)

        .when()
            .get("http://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json");

        String book_name = res.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(book_name,"The Lord of the Rings");
    }

    // Search for a book title
    @Test
    void testJSONresponseBodyDataBookSearch()
    {
        Response res = given()
            .contentType(ContentType.JSON)

        .when()
            .get("http://localhost:3000/store");

        JSONObject jo = new JSONObject(res.asString()); // Converting response AS(!) string to JSON Object type

        boolean status = false;

        for(int i = 0;i < jo.getJSONArray("book").length();i++)
        {
            String book_title = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if(book_title.equals("The Lord of the Rings")) {
                status = true;
                break;
            }
        }

        // The book has been found
        Assert.assertTrue(status);
    }

    // Check the total price of all books
    @Test
    void testJSONresponseBodyDataTotalPrice()
    {
        Response res = given()
            .contentType(ContentType.JSON)

        .when()
            .get("http://localhost:3000/store");

        JSONObject jo = new JSONObject(res.asString()); // Converting response AS(!) string to JSON Object type

        double total_price = 0;

        for(int i = 0;i<jo.getJSONArray("book").length();i++)
        {
            String bookprice = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            total_price = total_price + Double.parseDouble(bookprice);
        }

        Assert.assertEquals(total_price,526.0);
    }
}
