package com.management.projects.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static LocalDate parseStringToDateInYyyyMmDdFormat(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }

}
