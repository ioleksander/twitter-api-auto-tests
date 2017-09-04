package com.twitter.api.timeline;

import com.twitter.api.Launcher;
import org.testng.annotations.Test;

import static com.twitter.api.Managers.HelperManager.tweetHelper;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static com.twitter.api.Helpers.BaseHelper.isDateValid;
import static org.testng.Assert.assertTrue;

public class HomeTimelineTests extends Launcher {

    @Test
    public void createdAtDateFieldTest() {
        tweetHelper().addTestTweet();
        String createdAtDate =
        given().
                get("/statuses/user_timeline.json").
        then().
                extract().body().path("created_at[0]");
        assertTrue(isDateValid(createdAtDate));
    }
    
    @Test
    public void retweetCountFieldInitialStateIsZeroTest() {
        tweetHelper().addTestTweet();
        given().
                get("/statuses/user_timeline.json").
        then().
                body("retweet_count[0]", equalTo(0));
    }

    @Test
    public void retweetCountFieldIncrementingTest() {
        tweetHelper().addTestTweetAndRetweetIt();
        given().
                get("/statuses/user_timeline.json").
        then().
                body("retweet_count[0]", equalTo(1));
    }

    @Test
    public void textFieldTest() {
        String tweetText = tweetHelper().addTestTweet().path("text");
        given().
                get("/statuses/user_timeline.json").
        then().
                body("text[0]", is(tweetText));
    }
}
