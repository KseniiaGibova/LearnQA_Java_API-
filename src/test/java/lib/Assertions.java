package lib;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.*;


public class Assertions {
    public static void assertJsonByName (Response Response, String name, int expectedValue){
        Response
                .then()
                .assertThat()
                .body("$", hasKey(name));

        int value = Response.jsonPath().getInt(name);
        assertEquals(expectedValue, value, "Unexpected response text");
    }
    //new, for put
    public static void assertJsonByName (Response Response, String name, String expectedValue){
        Response
                .then()
                .assertThat()
                .body("$", hasKey(name));

        String value = Response.jsonPath().getString(name);
        assertEquals(expectedValue, value, "Unexpected response text");
    }


    public static void assertResponseTextEquals(Response Response, String expectedAnswer) {
        assertEquals(expectedAnswer, Response.asString(), "Unexpected response text");
    }

    public static void assertResponseCodeEquals(Response Response, int expectedStatusCode) {
        assertEquals(expectedStatusCode, Response.statusCode(), "Unexpected status code");
    }

    public static void assertJsonHasField(Response Response, String expectedFieldName) {
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }
    public static void assertJsonHasFields (Response Response, String[] expectedFieldNames) {
        for (String expectedFieldName: expectedFieldNames ) {
            Assertions.assertJsonHasField(Response, expectedFieldName );
        } }
    public static void assertJsonHasNotField (Response Response, String unexpectedFieldName) {
        Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
    }
}