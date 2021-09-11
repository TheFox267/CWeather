package com.example.cweather;

public class TextHandler {
    private static String text;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        TextHandler.text = text;
    }
}
