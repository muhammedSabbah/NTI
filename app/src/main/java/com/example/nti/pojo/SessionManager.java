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

    public SessionManager(Context mContext){
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(sharedPreferencesName, 0);
        editor = sharedPreferences.edit();
    }
    public void createData(String email, String password){
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
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
