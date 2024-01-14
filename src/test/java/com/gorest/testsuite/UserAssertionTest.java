package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }


//1. Verify the if the total record is 20
//2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
//3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
//4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
//5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
//6. Verify the status is “Active” of user name is “Amaresh Rana”
//7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”


    // 1) Verify that the products of limit is equal to 10
    @Test
    public void test001() {
        response.body("limit", equalTo(10));
    }

    // 2) Verify that the products of total is = 51957
    @Test
    public void test002() {
        //Homework
    }

    // 3) Check the Name 'Duracell - AA Batteries (8-Pack)' is available in List of product's name
    @Test
    public void test003() {
        response.body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"));
    }

    // 4) Check Multiple Names (Energizer - MAX Batteries AA (4-Pack), Duracell - 9V Batteries (2-Pack)) are available in list of product's name
    @Test
    public void test004() {
        //Homework
    }

    // 5) Verify the 'name' field inside first categories map for the first data (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {
        response.body("data[0].categories[0]", hasKey("name"));

    }

    // 6) Check entry 'manufacturer = Energizer' is inside map of product name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test006() {
        response.body("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}", hasItem(hasEntry("manufacturer", "Energizer")));

    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {
        response.body("limit", equalTo(10))
                .body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"))
                .body("data[0].categories[0]", hasKey("name"));
    }

    // 8) Logical Assertions
    @Test
    public void test008() {
        response.body("limit",equalTo(10))
                .body("limit", lessThan(11))
                .body("limit", greaterThan(9))
                .body("limit", greaterThanOrEqualTo(10));
    }
}
