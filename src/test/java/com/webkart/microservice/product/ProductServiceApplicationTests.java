package com.webkart.microservice.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @LocalServerPort
    private Integer port;


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void testCreateProduct() {
        String requestBody = """
                {
                     "name":"Samsung Fold",
                     "description":"A foldable phone from Samsung",
                     "price":147580.00
                 }
                """;
        RestAssured.given().contentType("application/json").body(requestBody)
                .when().post("/api/product")
                .then().statusCode(201).body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Samsung Fold"))
                .body("description", Matchers.equalTo("A foldable phone from Samsung"))
                .body("price", Matchers.equalTo(147580.00f));
        ;
    }
}
