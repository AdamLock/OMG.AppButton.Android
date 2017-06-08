package com.optimise.appbutton.utility;

import java.util.regex.Pattern;

/**
 * Utility class useful when dealing with string objects.
 * This class is a collection of static functions
 * it is not allowed to create instances of this class
 */
public abstract class StringUtils {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = "StringUtils";

    public static final String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * @param pStr String object to be tested.
     * @returns true if the given string is null or empty or contains spaces only.
     */
    public static boolean isNullOrEmpty(final String pStr) {
        return pStr == null || pStr.trim().length() == 0 || pStr.equals("null");
    }
}
