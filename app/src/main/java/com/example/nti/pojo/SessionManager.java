package com.example.nti.pojo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static String sharedPreferencesName = "NAME";
    private static String KEY_LOGIN = "IS_LOGIN";
    public static String KEY_EMAIL = "EMAIL";
    private static String KEY_PASSWORD = "PASSWORD";
    private static String KEY_MOVIE_TYPE = "MOVIE_TYPE";

    public static String getKeyMovieType() {
        return KEY_MOVIE_TYPE;
    }

    public static void setKeyMovieType(String keyMovieType) {
        KEY_MOVIE_TYPE = keyMovieType;
    }

    public static String getKeyEmail() {
        return KEY_EMAIL;
    }

    public static void setKeyEmail(String keyEmail) {
        KEY_EMAIL = keyEmail;
    }

    public static String getKeyPassword() {
        return KEY_PASSWORD;
    }

    public static void setKeyPassword(String keyPassword) {
        KEY_PASSWORD = keyPassword;
    }

    public SessionManager(Context mContext){
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(sharedPreferencesName, 0);
        editor = sharedPreferences.edit();
    }
    public void createData(String email, String password){
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_MOVIE_TYPE, "All");
        editor.commit();
    }
    public HashMap<String, String> getData(){
        HashMap<String, String> userData = new HashMap<>();
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);
        userData.put(KEY_EMAIL, email);
        userData.put(KEY_PASSWORD, password);
        return userData;
    }
    public void clearData(){
        editor.clear();
        editor.commit();
    }

    public boolean IS_LOGIN(){
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }
}
