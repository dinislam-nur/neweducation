package RestAssured_Examples;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Example1 {

    private static final String URI = "http://www.google.com/search?q=httpclient&btnG=Google+Search&aq=f&oq=";

    @Test
    void throw200_test() {
        given()
                .baseUri("http://www.google.com")
                .param("q", "httpclient")
                .param("btnG", "Google+Search")
                .param("aq", "f")
                .param("oq")
        .when()
                .get("/search")
        .then()
                .statusCode(200);

    }
}
