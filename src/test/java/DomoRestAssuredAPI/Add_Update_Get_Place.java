package DomoRestAssuredAPI;

import files.PayLoad;
import files.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Add_Update_Get_Place {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");

        System.out.println("place id : "+placeId);

        // update api
        String newPlace = "99, New Yark, USA 27";
        given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"place_id\": \""+placeId+"\",\n" +
                        "  \"address\": \""+newPlace+"\",\n" +
                        "  \"key\" : \"qaclick123\"\n" +
                        "}\n").when()
                .put("maps/api/place/update/json").then().log().all().assertThat()
                .statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get api

       response = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
                .body("address", equalTo("99, New Yark, USA 27")).extract().response().asString();

       JsonPath js1 = Utility.stringToJson(response);
       String actualAddress = js1.getString("address");

        Assert.assertEquals(newPlace,actualAddress);
    }
}
