package SpecDemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.PostResponse;
import pojoPayload.Location;
import pojoPayload.MapPayload;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpecRequestBuilder {
    public static void main(String[] args) {

        MapPayload m = new MapPayload();
        Location pojoPayLoad = new Location();
        List<String> type = new ArrayList<>();
        m.setAccuracy(50);
        m.setAddress("29, side layout, cohen 09");
        m.setName("Frontline house");
        m.setPhone_number("(+91) 983 893 3937");
        m.setWebsite("http://google.com");
        m.setLanguage("French-IN");
        pojoPayLoad.setLat("-38.383494");
        pojoPayLoad.setLng("33.427362");
        m.setLocation(pojoPayLoad);
        type.add("shoe park");
        type.add("shop");
        m.setTypes(type);

//        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
//                .body(m)
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
//
//        System.out.println(response);

       RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
               .addQueryParam("key","qaclick123")
               .setContentType(ContentType.JSON).build();
       ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        Response response1 = given().spec(req).body(m)
                .when().post("maps/api/place/add/json")
                .then().spec(res).extract().response();

        String responseString = response1.asString();
        System.out.println(responseString);

    }
}
