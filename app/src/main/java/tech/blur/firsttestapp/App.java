package tech.blur.firsttestapp;

import android.app.Application;
import android.content.Context;

import tech.blur.firsttestapp.core.AppComponent;
import tech.blur.firsttestapp.core.DaggerAppComponent;
import tech.blur.firsttestapp.core.modules.SharedPreferencesModule;

public class App extends Application {


    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
    }

    public App getApp (Context context){
        return (App) context.getApplicationContext();
    }
}
