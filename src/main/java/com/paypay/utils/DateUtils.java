package com.paypay.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateUtils {

    public static final String TIME_ZONE_PROPERTY = StringUtils.defaultIfBlank(System.getProperty("user.timezone"), "GMT");
    public static final ZoneId ZONE_ID = ZoneId.of(TIME_ZONE_PROPERTY);
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone(ZONE_ID.getId());

    public static final String ZONED_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    public static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER = createDateTimeFormatter(ZONED_DATE_TIME_PATTERN);
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = createDateTimeFormatter(LOCAL_DATE_TIME_PATTERN);
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = createDateTimeFormatter(LOCAL_DATE_PATTERN);

    static {
        setTimeZone();
    }

    private DateUtils() {
    }

    public static void setTimeZone() {
        TimeZone.setDefault(TIME_ZONE);
    }

    public static String localDateToString(ZonedDateTime date) {
        return ZONED_DATE_TIME_FORMATTER.format(date);
    }

    public static String localDateToString(LocalDateTime date) {
        return LOCAL_DATE_TIME_FORMATTER.format(date);
    }

    public static String localDateToString(LocalDate date) {
        return LOCAL_DATE_FORMATTER.format(date);
    }

    public static LocalDateTime stringToLocalDate(String stringValue) {
        return LocalDateTime.parse(stringValue, LOCAL_DATE_TIME_FORMATTER);
    }

    public static SimpleDateFormat createSimpleLocalDateTimeFormatter() {
        return new SimpleDateFormat(LOCAL_DATE_TIME_PATTERN);
    }

    private static DateTimeFormatter createDateTimeFormatter(String zonedDateTimePattern) {
        return DateTimeFormatter.ofPattern(zonedDateTimePattern).withZone(ZONE_ID);
    }
}
