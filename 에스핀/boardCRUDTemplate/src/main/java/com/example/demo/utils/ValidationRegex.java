package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    // 이메일 형식 체크
    public static boolean isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    /**
     * 비밀번호 형식 체크
     * 영문, 특수문자, 숫자 포함 8자 이상
     */
    public static boolean isRegexPW(String target) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }
}

