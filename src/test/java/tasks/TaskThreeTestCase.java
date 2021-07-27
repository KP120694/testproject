package tasks;

import builders.*;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TaskThreeTestCase {

    @Test
    public void updateAFixutreInDatabaseTestCase() {

        Teams team1 = Teams.builder().teamId("HOME").association("HOME").name("Chelsea").build();
        Teams team2 = Teams.builder().teamId("AWAY").association("AWAY").name("Kishan").build();
        ArrayList<Teams> teamsArrayList = new ArrayList<Teams>();
        teamsArrayList.add(team1);
        teamsArrayList.add(team2);

        Goals goals = Goals.builder().clockTime(640).confirmed(true).id(2).ownGoal(false).penalty(false).playerId(4433).teamId("HOME").build();
        FootballFullState footballFullState = FootballFullState.builder().homeTeam("Chelsea").awayTeam("Kishan").finished(true).gameTimeInSeconds(900).goals(goals).period("FIRST_HALF").possibles("").corners("").redCards("").startDateTime("2018-03-20T10:49:38.655Z").started(true).teams(teamsArrayList).build();
        FixtureStatus fixtureStatus = FixtureStatus.builder().displayed(false).suspended(true).build();
        Fixtures fixtures = Fixtures.builder().fixtureId("3").fixtureStatus(fixtureStatus).footballFullState(footballFullState).build();


        RestAssured.baseURI = "http://localhost:3000/fixture";


        ValidatableResponse response;
        response = given()
                .urlEncodingEnabled(true)
                .log().all()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(fixtures))
                .put(RestAssured.baseURI)
                .then();

        Assert.assertEquals(response.extract().statusCode(), HttpStatus.SC_NO_CONTENT);

        Response fixtureResponse;

        fixtureResponse = get("http://localhost:3000/fixture/3");
        Assert.assertEquals(HttpStatus.SC_OK, fixtureResponse.getStatusCode());

        String json = fixtureResponse.asString();
        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals("Kishan", jsonPath.get("footballFullState.awayTeam").toString());
    }

}
