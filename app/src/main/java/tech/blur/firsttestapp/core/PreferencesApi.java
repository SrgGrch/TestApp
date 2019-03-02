package tech.blur.firsttestapp.core;

import android.content.SharedPreferences;

public class PreferencesApi {

    private enum PrefsNames {POS}

    public static void setPos (int pos, SharedPreferences prefs){
        prefs.edit().putString(PrefsNames.POS.name(), Integer.toString(pos)).apply();
    }

    public static int getPos (SharedPreferences prefs){
        String tmp = prefs.getString(PrefsNames.POS.name(), null);
        if (tmp == null) return  0;
        else return Integer.parseInt(tmp);

    }
}
