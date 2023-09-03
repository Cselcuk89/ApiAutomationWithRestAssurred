package day1;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {
    /*
    given() : content type,set cookies, add auth, add params, set headers info etc...
    when() : indicates action with request methods
    then() : validate status code, extract response, extract headers,cookies and validate response body...
     */
    int id;
   @Test(priority = 1)
    public void getUsers(){
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    public void createUser(){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("name","pavan");
        hashMap.put("job","trainer");
         id = given()
                .contentType(ContentType.JSON)
                .body(hashMap)
                .when()
                .post("https://reqres.in/api/users").jsonPath().getInt("id");//used for getting the id value from create user response body and we will use it in the next test
//                .then()
//                .statusCode(201)
//                .log().all();

    }
    @Test(priority = 3,dependsOnMethods = "createUser")
    public void updateUser(){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("name","john");
        hashMap.put("job","teacher");
        given().
                contentType("application/json")
                .body(hashMap).
        when().
                put("https://reqres.in/api/users/" + id).//normally put url is https://reqres.in/api/users/2 but we parameterized it by using id field from create user response body
        then()
                .statusCode(200)
                .log().all();



    }
    @Test(priority = 4)
    public void deleteUser(){
       given()
               .when()
                  .delete("https://reqres.in/api/users/" + id)
               .then()
                  .statusCode(204)
                  .log().all();
    }
}
