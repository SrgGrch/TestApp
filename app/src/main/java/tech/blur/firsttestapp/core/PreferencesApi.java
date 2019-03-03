package tech.blur.firsttestapp.core;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import tech.blur.firsttestapp.core.model.Post;

public class PreferencesApi {

    private enum PrefsNames {POS, POSTS}

    public static void setPos (int pos, SharedPreferences prefs){
        prefs.edit().putString(PrefsNames.POS.name(), Integer.toString(pos)).apply();
    }

    public static int getPos (SharedPreferences prefs){
        String tmp = prefs.getString(PrefsNames.POS.name(), null);
        if (tmp == null) return  0;
        else return Integer.parseInt(tmp);

    }

    public static void setPosts (List<Post> posts, SharedPreferences prefs){
        JSONArray jsonArray = new JSONArray(posts);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Post>>(){}.getType();
        String json = gson.toJson(posts, type);
        prefs.edit().putString(PrefsNames.POSTS.name(), json).apply();
    }

    public static ArrayList<Post> getPosts (SharedPreferences prefs){
        String tmp = prefs.getString(PrefsNames.POSTS.name(), null);
        if (tmp == null) return  null;
        else{
            Gson gson = new Gson();
            Type type = new TypeToken<List<Post>>(){}.getType();
            return gson.fromJson(tmp, type);
        }

    }

}
