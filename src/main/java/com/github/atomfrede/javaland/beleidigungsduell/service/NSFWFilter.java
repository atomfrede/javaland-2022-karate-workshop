package com.github.atomfrede.javaland.beleidigungsduell.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class NSFWFilter {

    private static List<String> badWords = Arrays.asList(
            "fick",
            "arsch",
            "wichs",
            "fotz",
            "anal",
            "möse",
            "penis"
    );
    public static String filter(String text) {

        String safe = text;
        for (String badWord : badWords) {
            safe = StringUtils.replaceIgnoreCase(safe, badWord, StringUtils.substring(badWord, 0, 1) + "*".repeat(badWord.length() - 1));
        }

        return safe;

    }
}
