package com.paypay.utils;

import java.util.Optional;
import java.util.function.Function;

public class SafeUtils {

    private SafeUtils() {
    }

    public static String getMessageSafe(Exception exception) {
        return Optional.ofNullable(exception)
                .map(e -> StringUtils.isBlank(e.getMessage()) ? e.getClass().getSimpleName() : e.getMessage())
                .orElse("Exception is null");
    }

    public static <T, R> R getNullable(T origin, Function<T, R> function) {
        return Optional.ofNullable(origin)
                .map(function)
                .orElse(null);
    }
}
