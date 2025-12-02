package com.charlie.urlshortner.util;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long value, int length) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        // Reverse and pad with leading '0' (or any char)
        String result = sb.reverse().toString();
        return String.format("%" + length + "s", result).replace(' ', '0');
    }

}
