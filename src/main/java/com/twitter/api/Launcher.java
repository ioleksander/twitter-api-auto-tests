package com.twitter.api;

import com.twitter.api.Utils.PropertyLoaderUtil;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;

public class Launcher {

    @BeforeSuite
    public static void setup() {
        setupRestAssured();
    }

    private static void setupRestAssured() {
        baseURI = PropertyLoaderUtil.loadProperty("base.uri");
        basePath = PropertyLoaderUtil.loadProperty("base.path");
        authentication = oauth(
                PropertyLoaderUtil.loadProperty("api.consumer_key"),
                PropertyLoaderUtil.loadProperty("api.consumer_secret"),
                PropertyLoaderUtil.loadProperty("api.token"),
                PropertyLoaderUtil.loadProperty("api.token_secret"));
    }
}
