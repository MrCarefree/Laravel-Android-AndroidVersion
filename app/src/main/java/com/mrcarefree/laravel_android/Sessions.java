package com.mrcarefree.laravel_android;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {
    private static  Sessions sessions;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;
    private static final String PREFERENCES_NAME = "sessions";

    public static String name = "name";
    public static String email = "email";

    public Sessions(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public static Sessions getInstance(Context context){
        if (sessions == null){
            sessions = new Sessions(context);
        }
        return sessions;
    }

    public void putString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
