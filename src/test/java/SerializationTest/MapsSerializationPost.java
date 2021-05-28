package SerializationTest;
import static io.restassured.RestAssured.*;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import pojo.MapResponse;
import pojo.PostResponse;
import pojoPayload.Location;
import pojoPayload.MapPayload;
import scala.concurrent.impl.FutureConvertersImpl;

import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;

public class MapsSerializationPost {
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

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(m)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        System.out.println("\n-------------------------------  json response ------------------------------------\n");
         String res = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(m)
                .when().post("maps/api/place/add/json")
                .asString();

        System.out.println(res);

        JsonPath j = new JsonPath(res);
        String place =  j.get("place_id");
        System.out.println("Place ID : "+place);

        System.out.println("------------------------------Desirialize---------------------------------- ");

        PostResponse pr = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(m)
                .when().post("maps/api/place/add/json")
                .as(PostResponse.class);

        System.out.println("Status : "+pr.getStatus());
        System.out.println("Place ID : "+pr.getPlace_id());
        System.out.println("Scope : "+pr.getScope());
        System.out.println("Reference : "+pr.getReference());
        System.out.println("ID : "+pr.getId());

    }
}
