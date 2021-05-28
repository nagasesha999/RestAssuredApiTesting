package DomoRestAssuredAPI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.PayLoad;
import io.restassured.RestAssured;

public class TestRestAssured1 {
    public static void main(String[] args) {
       RestAssured.baseURI = "https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);

    }
}
