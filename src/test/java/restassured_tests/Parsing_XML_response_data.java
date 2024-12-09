package restassured_tests;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Parsing_XML_response_data {

    /* Basic approach
    The XML response is:
    -------------------------------
    <root>
        <city>San Jose</city>
        <firstName>John</firstName>
        <lastName>Doe</lastName>
        <state>CA</state>
    </root>
    -------------------------------
     */
    @Test
    void testXMLresponseBasic()
    {
        given()
            .contentType(ContentType.XML)

        .when()
            .get("https://mocktarget.apigee.net/xml")

        .then()
            .statusCode(200)
            .header("Content-Type","application/xml; charset=utf-8")
            .body("root.city",equalTo("San Jose")); // Uses XPath (XmlPath) with "." instead of "/"
    }

    /* Approach with more possibilities
    The XML response is:
    -------------------------------
    <root>
        <city>San Jose</city>
        <firstName>John</firstName>
        <lastName>Doe</lastName>
        <state>CA</state>
    </root>
    -------------------------------
     */
    @Test
    void testXMLresponseAdvanced()
    {
        Response res = given()
            .contentType(ContentType.XML)

        .when()
            .get("https://mocktarget.apigee.net/xml");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");

        String city_name = res.xmlPath().get("root.city").toString();    // xmlPath is the same as XPath, uses "." instead of "/"
        Assert.assertEquals(city_name,"San Jose");
    }

    @Test
    void testPetsInPetStore()
    {
        Response res = given()
            .contentType(ContentType.XML)
            .accept("application/xml")

        .when()
            .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml");

        // Get the id of the first pet in the pet store (index 0)
        String first_pet_id = res.xmlPath().get("pets.Pet.id[0]").toString();
        System.out.println("The id of the first pet in the pet store is:  " + first_pet_id);

        // Count the number of pets in the pet store
        XmlPath xmlobj1 = new XmlPath(res.asString());
        List<String> all_pets = xmlobj1.getList("pets.Pet");
        System.out.println("There are " + all_pets.size() + " pets available in the pet store.");

        // List the id's of all pets in the pet store
        XmlPath xmlobj2 = new XmlPath(res.asString());
        List<String> all_pets_id = xmlobj2.getList("pets.Pet.id");
        for (String petid:all_pets_id)
        {
            System.out.println(petid);
        }
    }
}
