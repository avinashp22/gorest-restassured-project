package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PostsAssertionTest {

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

//1. Verify the if the total record is 25
//2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupticohaero libero.”
//3. Check the single user_id in the Array list (5914249)
//4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
//5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus. Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa ususvos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptiotumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autusacerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response = given()
                .when()
                .get("/users?page=1&per_page=25")
                .then().statusCode(200);
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 25);
    }

    //2. Verify the if the title of id =  93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void test002(){
        response.body("[2].title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }
    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003(){
        response.body("user_id", hasItem(5914249));
    }
    //4. Check the multiple ids in the ArrayList (5914243, 5914206, 5914193)
    @Test
    public void test004(){
        response.body("user_id", hasItems(5914243, 5914206, 5914193));
    }
    //5. Verify the body of userid = 5914184 is equal “Cinis non solum. Decretum auctus artificiose. Bos umerus totam. Sed cicuta debitis. Crur unde tum. Et tabella dignissimos. Cognomen vito bardus. Deduco ara una. Desparatus amet caste. Quis taedium sollers.”
    @Test
    public void test005(){
        response.body("find{it.user_id == 5914184 }.body", equalTo("Cinis non solum. Decretum auctus artificiose. Bos umerus totam. Sed cicuta debitis. Crur unde tum. Et tabella dignissimos. Cognomen vito bardus. Deduco ara una. Desparatus amet caste. Quis taedium sollers."));
    }

}
