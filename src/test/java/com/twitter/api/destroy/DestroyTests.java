package com.twitter.api.destroy;

import com.twitter.api.Launcher;
import org.testng.annotations.Test;

import static com.twitter.api.Managers.HelperManager.tweetHelper;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class DestroyTests extends Launcher {

    @Test
    public void tweetCanBeRemovedTest() {
        String tweetText = tweetHelper().getRandomTweetText();

        // Add a tweet & get it's id
        long tweetId = given().
                                queryParam("status", tweetText).
                        when().
                                post("/statuses/update.json").
                        then().
                                extract().body().path("id");

        // Remove the tweet
        post("/statuses/destroy/" + tweetId + ".json");

        // Verify that the tweet is not available
        given().
                get("/statuses/show/" + tweetId + ".json").
        then().
                statusCode(404);
    }

    @Test
    public void error403IfTryToRemoveNotOwnTweetTest() {
        given().
                post("/statuses/destroy/903976732037849088.json").
        then().
                statusCode(403);
    }
}
