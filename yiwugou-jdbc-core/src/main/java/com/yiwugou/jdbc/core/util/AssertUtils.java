package com.yiwugou.jdbc.core.util;

public class AssertUtils {
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
