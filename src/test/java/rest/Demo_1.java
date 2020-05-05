package rest;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Demo_1
{
    public static JSONObject json = new JSONObject();


    @Test
    public void Demo1_GET_get_all_countries_Verify_POLAND_included()
    {
        given()

            .when()
                    .get("https://my-json-server.typicode.com/adrianszczesny0/rest/Infected")
            .then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                .assertThat()
                    .body(containsString("POLAND"));
    }


    @BeforeMethod
    public void postdata()
    {
        json.put("id",5);
        json.put("country","UK");
        json.put("count",666);
        RestAssured.baseURI="https://my-json-server.typicode.com/adrianszczesny0/rest/Infected";

    }
    @Test
    public void Demo1_POST_add_new_Infected_country()
    {
        given()
                .contentType("application/json; charset=utf-8")
                .body(json.toJSONString())
            .when()
                .post()
            .then()
                .statusCode(201)
                .header("Content-Type","application/json; charset=utf-8")
                .statusLine("HTTP/1.1 201 Created")
                .assertThat()
                    .body("id",equalTo(5))
                    .body("country",equalTo("UK"))
                    .body("count",equalTo(666));
    }
}
