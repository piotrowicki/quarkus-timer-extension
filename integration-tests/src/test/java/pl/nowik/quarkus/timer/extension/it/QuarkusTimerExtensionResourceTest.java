package pl.nowik.quarkus.timer.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusTimerExtensionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/quarkus-timer-extension")
                .then()
                .statusCode(200)
                .body(is("Hello quarkus-timer-extension"));
    }
}
