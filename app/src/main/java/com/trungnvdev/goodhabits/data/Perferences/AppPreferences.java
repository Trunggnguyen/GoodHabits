package com.trungnvdev.goodhabits.data.Perferences;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AppPreferences {


    public static void setIntroData(Context context, String name, int gender) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.putString("Name",name);
        editor.putInt("gioitinh",gender);
        editor.apply();
    }

    public static boolean isIntroData(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    public static int gender(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        int isGender = pref.getInt("gioitinh",1);
        return  isGender;
    }

    public static String getName(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        String isGender = pref.getString("Name","admin");
        return  isGender;
    }
    public static void setTime(Context context, String time) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Time",time);
        editor.apply();
    }
    public static String getTime(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        String isGender = pref.getString("Time","08:00");
        return  isGender;
    }




}
