package day2;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DiffWaysToCreatePostRequestBody {

            /*
            Different ways to create POST request body
            1) Post request body using Hashmap
            2) Post request body creation using using Org.JSON
            3) Post request body creation using POJO class
            4) Post request using external json file data
            */
    //1) Post request body using Hashmap
    @Test
    public void testPostUsingHashMap(){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456");

        String[] courseArr = {"C","C++"};
        data.put("courses", courseArr);

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"),
                        "location",equalTo("France"),
                        "phone",equalTo("123456"),
                        "courses[0]",equalTo("C"),
                        "courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();



    }
    //2) Post request body using org.json libray
    @Test
    public void testPostUsingJsonLibrary(){
        JSONObject data = new JSONObject();

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456");

        String[] coursesArr = {"C","C++"};
        data.put("courses", coursesArr);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"),
                        "location",equalTo("France"),
                        "phone",equalTo("123456"),
                        "courses[0]",equalTo("C"),
                        "courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

}
