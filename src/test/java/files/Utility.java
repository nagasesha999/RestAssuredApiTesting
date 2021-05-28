package files;

import io.restassured.path.json.JsonPath;

public class Utility {

    public static JsonPath stringToJson(String response){
        JsonPath jsonpath = new JsonPath(response);
        return jsonpath;
    }
}
