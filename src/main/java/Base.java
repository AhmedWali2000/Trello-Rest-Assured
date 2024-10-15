import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Base {
    private static final String key = "fe6a1eb38397f8c46b701fc5b6be6be9";
    private static final String token = "ATTAba801cc4592643cb1af4db4ad44c94f10cc0761380a50440357b23442ffe10fbF07B9044";

    public static RequestSpecification request(){
        return given().log().all()
                .baseUri("https://api.trello.com/1/")
                .queryParams("key", key, "token", token)
                .contentType("application/json");
    }

}
