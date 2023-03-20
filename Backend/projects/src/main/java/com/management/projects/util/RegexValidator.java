package com.management.projects.util;

import java.util.regex.Pattern;

public class RegexValidator {

    private static final Pattern passwordValidationRegex = Pattern.compile(
            "^\\w+[[\\.\\-\\_]?\\w]*@\\w+[[\\.\\-\\_]?\\w]*[^\\.]+\\.\\w{2,6}$");

    public static boolean isEmail(String email){
        return email.matches(String.valueOf(passwordValidationRegex));
    }

}
