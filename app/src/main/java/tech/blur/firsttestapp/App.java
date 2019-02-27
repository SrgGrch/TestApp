package tech.blur.firsttestapp;

import android.app.Application;
import android.content.Context;

import tech.blur.firsttestapp.core.AppComponent;

public class App extends Application {


    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //component = DaggerAppComponent.builder().build();

    }

    public App getApp (Context context){
        return (App) context.getApplicationContext();
    }
}
