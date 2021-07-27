package tasks;

import builders.*;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TaskFourTestCase {

    @Test
    public void deleteAFixtureTestCase() {

        Teams team1 = Teams.builder().teamId("HOME").association("HOME").name("Chelsea").build();
        Teams team2 = Teams.builder().teamId("AWAY").association("AWAY").name("Farsley").build();

        ArrayList<Teams> teamsArrayList = new ArrayList<Teams>();
        teamsArrayList.add(team1);
        teamsArrayList.add(team2);
        Goals goals = Goals.builder().clockTime(640).confirmed(true).id(2).ownGoal(false).penalty(false).playerId(4433).teamId("HOME").build();
        FootballFullState footballFullState = FootballFullState.builder().homeTeam("Chelsea").awayTeam("Farsley").finished(true).gameTimeInSeconds(900).goals(goals).period("FIRST_HALF").possibles("").corners("").redCards("").startDateTime("2018-03-20T10:49:38.655Z").started(true).teams(teamsArrayList).build();
        FixtureStatus fixtureStatus = FixtureStatus.builder().displayed(false).suspended(true).build();
        Fixtures fixtures = Fixtures.builder().fixtureId("5").fixtureStatus(fixtureStatus).footballFullState(footballFullState).build();


        RestAssured.baseURI = "http://localhost:3000/fixture";
        ValidatableResponse response;

            response = given()
                    .urlEncodingEnabled(true)
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new Gson().toJson(fixtures))
                    .post(RestAssured.baseURI)
                    .then();

        RestAssured.baseURI = "http://localhost:3000/fixture/";

        response = given()
                .when()
                .delete("5").then();

        Assert.assertEquals(response.extract().statusCode(), HttpStatus.SC_NO_CONTENT);

        Response deletedFixture = get(baseURI + "5");

        Assert.assertEquals(deletedFixture.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
