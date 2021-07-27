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


public class TaskTwoTestCase {


    @Test
    public void addNewFixtureToDatabaseTestCase() {
        // Clean up before test
       Response cleanUpResponse = get("http://localhost:3000/fixture/4");
        if (cleanUpResponse.getStatusCode() == HttpStatus.SC_OK) {
            RestAssured.baseURI = "http://localhost:3000/fixture/";
            given().when()
                    .delete("4").then();
        }

        Teams team1 = Teams.builder().teamId("HOME").association("HOME").name("Chelsea").build();
        Teams team2 = Teams.builder().teamId("AWAY").association("AWAY").name("Farsley").build();
        ArrayList<Teams> teamsArrayList = new ArrayList<Teams>();
        teamsArrayList.add(team1);
        teamsArrayList.add(team2);

        Goals goals = Goals.builder().clockTime(640).confirmed(true).id(2).ownGoal(false).penalty(false).playerId(4433).teamId("HOME").build();
        FootballFullState footballFullState = FootballFullState.builder().homeTeam("Chelsea").awayTeam("Farsley").finished(true).gameTimeInSeconds(900).goals(goals).period("FIRST_HALF").possibles("").corners("").redCards("").startDateTime("2018-03-20T10:49:38.655Z").started(true).teams(teamsArrayList).build();
        FixtureStatus fixtureStatus = FixtureStatus.builder().displayed(false).suspended(true).build();
        Fixtures fixtures = Fixtures.builder().fixtureId("4").fixtureStatus(fixtureStatus).footballFullState(footballFullState).build();


        RestAssured.baseURI = "http://localhost:3000/fixture";
        ValidatableResponse response;

        response = given()
                .urlEncodingEnabled(true)
                .log().all()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(fixtures))
                .post(RestAssured.baseURI)
                .then();
        Assert.assertEquals(HttpStatus.SC_ACCEPTED, response.extract().statusCode());

        Response fixtureResponse;
        do {
         fixtureResponse = get("http://localhost:3000/fixture/4");
        } while (fixtureResponse.getStatusCode() == HttpStatus.SC_NOT_FOUND);

        Assert.assertEquals(HttpStatus.SC_OK, fixtureResponse.getStatusCode());

        String json = fixtureResponse.asString();
        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals("4", jsonPath.get("fixtureId").toString());
        Assert.assertEquals("HOME", jsonPath.get("footballFullState.teams.teamId[0]").toString());

    }


}


