package com.gorest.testbase;

import com.gorest.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt() {

        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseUrl");  //with propertyreader
        RestAssured.basePath = PropertyReader.getInstance().getProperty("basePath");  //with propertyreader

    }
}
