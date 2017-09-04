package com.twitter.api.update;

import com.twitter.api.Launcher;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.twitter.api.Managers.HelperManager.tweetHelper;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;


public class UpdateTests extends Launcher {

    @Test
    public void tweetTextCanBeUpdatedTest() {
        String tweetText = tweetHelper().getRandomTweetText();

        given().
                queryParam("status", tweetText).
        when().
                post("/statuses/update.json").
        then().
                body("text", is(tweetText));
    }

    @Test
    public void error403IfStatusIsEmptyTest() {
        given().
                queryParam("status", "").
        when().
                post("/statuses/update.json").
        then().
                statusCode(403);
    }

    @Test
    public void error403IfStatusHasSpacesOnlyTest() {
        given().
                queryParam("status", "   ").
        when().
                post("/statuses/update.json").
        then().
                statusCode(403);
    }

    @Test
    public void error403IfTweetDuplicatedTest() {
        String tweetText = tweetHelper().getRandomTweetText();

        Response resp = null;
        for (int i=0; i < 2; i++) {
            resp = given().
                        queryParam("status", tweetText).
                    when().
                        post("/statuses/update.json").then().extract().response();
        }

        resp.then().statusCode(403);
    }

    @Test
    public void error400IfNotLoggedInTest() {
        String tweetText = tweetHelper().getRandomTweetText();

        given().
                auth().none().
                queryParam("status", tweetText).
        when().
                post("/statuses/update.json").
        then().
                statusCode(400);
    }
}
