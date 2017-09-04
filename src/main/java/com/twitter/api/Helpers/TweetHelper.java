package com.twitter.api.Helpers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TweetHelper extends BaseHelper {

    public Response addTestTweet() {
        return given().
                        queryParam("status", getRandomTweetText()).
                when().
                        post("/statuses/update.json").
                then().
                        extract().response();
    }

    private void retweetById(long id) throws NullPointerException {
        given().
                queryParam("id", id).
        when().
                post("/statuses/retweet/" + id + ".json").
        then().
                log().ifError();
    }

    public void addTestTweetAndRetweetIt() {
        long tweetId = addTestTweet().path("id");
        retweetById(tweetId);
    }

    // Test data generator

    public String getRandomTweetText() {
        String chuckNorrisFact = faker.chuckNorris().fact().replace("'", "’");
        return chuckNorrisFact.substring(0, Math.min(chuckNorrisFact.length(), 115)) + " - " + getCurrentDateAndTime();
    }
}
