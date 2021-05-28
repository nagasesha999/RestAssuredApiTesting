package DomoRestAssuredAPI;

import files.PayLoad;
import files.Utility;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.AddResponse;
import pojoPayload.Location;
import pojo.MapResponse;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadResponseUsingPOGO {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.defaultParser = Parser.JSON;
//        MapResponse m = new MapResponse();
//        Location l = new Location();
//        m.setAccuracy(99);
//        m.setAddress("23 india ap 27");
//        m.setName("nagasesha");
//        m.setPhone_number("(+91) 9515599998");
//        m.setTypes("office");
//        m.setWebsite("http://google.com");
//        m.setLanguage("English");
//        l.setLatitude("-38.383494");
//        l.setLongitude("33.427362");
//        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
//                .body(PayLoad.addPlace())
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

//        JsonPath js = new JsonPath(response);
//        String placeId = js.getString("place_id");
//        System.out.println("place id : "+placeId);

        AddResponse response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .as(AddResponse.class);




        String  placeId = response.getPlace_id();
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

        MapResponse mr = given().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .as(MapResponse.class);

        System.out.println(mr.getName());
        System.out.println(mr.getLocation().getLongitude());
        System.out.println(mr.getLocation().getLongitude());
    }

}
