package tasks;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static io.restassured.RestAssured.get;

public class TaskOneTestCase {

    private static String fixturesUrl = "http://localhost:3000/fixtures";

 // i.
    @Test
    public void testAll3FixturesReturnedTestCase()  {
        Response response = get(fixturesUrl);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList<String> listOfFixtures = jsonPath.get("fixutureId");
        Assert.assertEquals(3, listOfFixtures.size());
    }

   // ii.
    @Test
    public void testAllFixturesHaveAValueTestCase() {
        Response response = get(fixturesUrl);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals("[1, 2, 3]", jsonPath.get("fixtureId").toString());
    }



}
