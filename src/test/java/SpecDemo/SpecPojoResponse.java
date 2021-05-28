package SpecDemo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.PostResponse;
import pojoPayload.Location;
import pojoPayload.MapPayload;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecPojoResponse {

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

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        RequestSpecification res1 = given().spec(req).body(m);
        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        PostResponse response1 = given().spec(req).body(m)
                .when().post("maps/api/place/add/json")
                .as(PostResponse.class);

        System.out.println("Status : "+response1.getStatus());
        System.out.println("Place ID : "+response1.getPlace_id());
        System.out.println("Reference : "+response1.getReference());
        System.out.println("ID : "+response1.getId());
        System.out.println("Scope : "+response1.getScope());
    }

}
