package com.twitter.api.Utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoaderUtil {

    private static final String PROPERTY_FILE = "/application.properties";

    public static String loadProperty(String propertyKey) {
        String propertyValue = null;
        Properties properties = new Properties();
        try {
            properties.load(PropertyLoaderUtil.class.getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (propertyKey != null) {
            propertyValue = properties.getProperty(propertyKey);
        }

        return propertyValue;
    }
}
