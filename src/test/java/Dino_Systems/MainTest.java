package Dino_Systems;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MainTest {

    @Test
    void throw403_ifUserDidNotAuthenticate() {
        int companyID = 100;
        String name = "Vasya";
        given()
                .baseUri("http://some_domain.com")
                .pathParam("companyID", companyID)
                .param("name", name)
        .when().get("/company/{companyID}/users")
        .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
