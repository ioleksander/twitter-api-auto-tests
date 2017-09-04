package com.twitter.api.Managers;

import com.twitter.api.Helpers.TweetHelper;

public class HelperManager {

    private static TweetHelper tweetHelper;

    public static TweetHelper tweetHelper() {
        if (tweetHelper != null) return tweetHelper;
        else {
            return tweetHelper = new TweetHelper();
        }
    }

}
