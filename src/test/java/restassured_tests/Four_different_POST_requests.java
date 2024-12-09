package restassured_tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Four_different_POST_requests {

    // Approach 1: POST request body using HashMap
    @Test
    void testPOSTusingHashMap()
    {
        HashMap data = new HashMap();

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","2345678901");

        // String array
        String coursesArr[] = {"C","C++"};

        data.put("courses", coursesArr);

        given()
            .contentType("application/json")
            .body(data)

        .when()
            .post("http://localhost:3000/students")

        .then()
            .statusCode(201)
            .body("name",equalTo("Scott"))
            .body("location",equalTo("France"))
            .body("phone",equalTo("2345678901"))
            .body("courses[0]",equalTo("C"))
            .body("courses[1]",equalTo("C++"))
            .header("Content-Type","application/json")
            .log().all();
    }

    // Approach 2: POST request body using org.json
    @Test
    void testPOSTusingJsonLibrary()
    {
        JSONObject data = new JSONObject();

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","2345678901");

        // String array
        String coursesArr[] = {"C","C++"};

        data.put("courses", coursesArr);

        given()
            .contentType("application/json")
            .body(data.toString())

        .when()
            .post("http://localhost:3000/students")

        .then()
            .statusCode(201)
            .body("name",equalTo("Scott"))
            .body("location",equalTo("France"))
            .body("phone",equalTo("2345678901"))
            .body("courses[0]",equalTo("C"))
            .body("courses[1]",equalTo("C++"))
            .header("Content-Type","application/json")
            .log().all();
    }

    // Approach 3: POST request body using POJO Class
    @Test
    void testPOSTusingPOJOclass()
    {
        POJO_POST_request_class data = new POJO_POST_request_class();

        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("2345678901");

        // String array
        String coursesArr[] = {"C","C++"};

        data.setCourses(coursesArr);

        given()
            .contentType("application/json")
            .body(data)

        .when()
            .post("http://localhost:3000/students")

        .then()
            .statusCode(201)
            .body("name",equalTo("Scott"))
            .body("location",equalTo("France"))
            .body("phone",equalTo("2345678901"))
            .body("courses[0]",equalTo("C"))
            .body("courses[1]",equalTo("C++"))
            .header("Content-Type","application/json")
            .log().all();
    }

    // Approach 4: POST request body using external JSON File
    @Test
    void testPOSTusingExternalJSONfile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
       JSONTokener jt = new JSONTokener(fr);
       JSONObject data = new JSONObject(jt);

        given()
            .contentType("application/json")
            .body(data.toString())

        .when()
            .post("http://localhost:3000/students")

        .then()
            .statusCode(201)
            .body("name",equalTo("Scott"))
            .body("location",equalTo("France"))
            .body("phone",equalTo("2345678901"))
            .body("courses[0]",equalTo("C"))
            .body("courses[1]",equalTo("C++"))
            .header("Content-Type","application/json")
            .log().all();
    }
}
