package com.cg.utils;

import java.util.regex.Pattern;

public class ValidDateUtils {
    public static final String NUMBER_REGEX = "^\\d+";

    public static boolean isNumberValid(String password) {
        return Pattern.compile(NUMBER_REGEX).matcher(password).matches();
    }
}
