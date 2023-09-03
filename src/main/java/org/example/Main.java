package org.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //validating if AddPlace api is working as expected
        RestAssured.baseURI = "http://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("""
                        {
                          "location": {
                            "lat": -38.383494,
                            "lng": 33.427362
                          },
                          "accuracy": 50,
                          "name": "Frontline house",
                          "phone_number": "(+91) 983 893 3937",
                          "address": "29, side layout, cohen 09",
                          "types": [
                            "shoe park",
                            "shop"
                          ],
                          "website": "http://google.com",
                          "language": "French-IN"
                        }""").when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(301).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)");
    }
}