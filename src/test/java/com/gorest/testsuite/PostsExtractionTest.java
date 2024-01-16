package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

//1. Extract the title
//2. Extract the total number of record
//3. Extract the body of 15th record
//4. Extract the user_id of all the records
//5. Extract the title of all the records
//6. Extract the title of all records whose user_id = 5914200
//7. Extract the body of all records whose id = 93957

    //1. Extract the title
    @Test
    public void test001() {
        List<String> titles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title  is : " + titles);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        int size = response.extract().path("size()");
        System.out.println("------------------Test start---------------------------");
        System.out.println("Total number of records are :" + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------Test start---------------------------");
        System.out.println("The body of 15th record: " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> usersId = response.extract().path("user_id");
        System.out.println("------------------Title---------------------------");
        System.out.println("The user_id of all the records : " + usersId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> allTitle = response.extract().path("title");
        System.out.println("------------------Title---------------------------");
        System.out.println("The title of all the records :" + allTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006() {
        List<HashMap<String, ?>> userTitle = response.extract().path("findAll{it.user_id==5914200}.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the title of all records whose user_id = 5914200" + userTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 93957
    @Test
    public void test007() {
        List<HashMap<String, ?>> userBody = response.extract().path("findAll{it.id==93957}.body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the body of all records whose id = 93957" + userBody);
        System.out.println("------------------End of Test---------------------------");
    }
}
