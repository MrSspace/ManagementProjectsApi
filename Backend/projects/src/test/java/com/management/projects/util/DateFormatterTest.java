package com.management.projects.util;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static com.management.projects.util.DateFormatter.parseStringToDateInYyyyMmDdFormat;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatterParseStringToDateInYyyyMmDdFormatTest {

    @Test
    public void parse_date_when_format_is_correct(){
        LocalDate CONTROL_DATE = LocalDate.of(2020,10,05);
        String date = "2020-10-05";
        LocalDate parsedDate = parseStringToDateInYyyyMmDdFormat(date);
        assertEquals(CONTROL_DATE, parsedDate);
    }

    @Test
    public void throw_DateTimeException_when_date_format_is_bad(){
        String date = "2020-10";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

    @Test
    public void throw_DateTimeException_when_date_format_is_bad_in_year(){
        String date = "20-10-05";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

    @Test
    public void throw_DateTimeException_when_date_is_empty(){
        String date = "";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

    @Test
    public void throw_DateTimeException_when_date_is_larger(){
        String date = "2020-010-005";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

    @Test
    public void throw_DateTimeException_when_date_use_slash(){
        String date = "2020/10/05";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

    @Test
    public void throw_DateTimeException_when_date_use_colon(){
        String date = "2020:10:05";
        assertThrows( DateTimeException.class,() -> parseStringToDateInYyyyMmDdFormat(date) );
    }

}