package com.paypay.utils;

public class StringUtils {

    public static String defaultIfBlank(String value, String defaultValue) {
        if (value == null || value.isBlank())
            return defaultValue;
        return value;
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
