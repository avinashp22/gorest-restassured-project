package com.gorest.testbase;

import com.gorest.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class TestBase {

    @BeforeClass
    public void inIt() {
        //RestAssured.baseURI = "https://gorest.co.in"; /without propertyreader
       // RestAssured.basePath = "/public/v2";      /without propertyreader
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseUrl");  //with propertyreader
        RestAssured.basePath = PropertyReader.getInstance().getProperty("basePath");  //with propertyreader


    }
}
