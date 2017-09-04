package com.twitter.api.Helpers;

import com.github.javafaker.Faker;
import com.twitter.api.Launcher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseHelper extends Launcher {

    // Test data generators

    static Faker faker = new Faker();

    static String getCurrentDateAndTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    // Data validators

    public static boolean isDateValid(String dateToValidate) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateToValidate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
