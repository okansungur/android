package com.example.guess3;
import android.content.Context;
import android.content.SharedPreferences;
public class Preferences {
    String temp = "";

    public String readSharedPreference(Context context, String prefName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return temp = sharedPreferences.getString(key, null);
    }

    public void writeSharedPreference(Context context, String prefName, String key, String value) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.commit();
        editor.putString(key, value);
        editor.commit();
    }
}
