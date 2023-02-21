package com.paypay.utils;

public class StringUtils {

    public static String defaultIfBlank(String value, String defaultValue) {
        if (value == null || value.isBlank())
            return defaultValue;
        return value;
    }
}
