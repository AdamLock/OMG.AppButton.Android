package com.optimise.appbutton.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author anoop.singh
 *         This class contain methods and keys to store/retrieve values to/from
 *         shared-prefs.
 */
public class Preferences {

    public static final String APPBUTTON_PREFERENCES = "appbutton_preferences";


    /**
     * @param key
     * @param defValue
     * @param context
     * @return
     */
    public static String getString(String key, String defValue, Context context) {
        return getSharedPreferences(context).getString(key, defValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @param context
     */
    public static void putString(String key, String defaultValue, Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, defaultValue);
        editor.commit();
    }

    /**
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Preferences.APPBUTTON_PREFERENCES, Context.MODE_PRIVATE);
        return pref;
    }

    /**
     * Clear all values from preferences
     *
     * @param context
     */
    public static void clearAllValues(Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
