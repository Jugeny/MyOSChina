package com.example.administrator.myoschina.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.administrator.myoschina.App;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PreferencesUtils {
    public static String PREFERENCE_NAME="oschina";
    public PreferencesUtils(){
    }
    //存在一个String键对值
    public static boolean putString(String key,String value){
        SharedPreferences pref= App.getContext().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(key,value);
        //保存
        return editor.commit();
    }
    public static boolean putInt(String key,int value){
        SharedPreferences pref= App.getContext().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putInt(key,value);
        //保存
        return editor.commit();
    }
    public static String getString(String key,@Nullable String defaultValue){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        return sharedPreferences.getString(key,defaultValue);
    }
    public static String getString(String key){
        return getString(key,null);
    }
    public static int getInt(String key,@Nullable int defaultValue){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        return sharedPreferences.getInt(key,defaultValue);
    }
    public static int getInt(String key){
        return  getInt(key,0);
    }

}
