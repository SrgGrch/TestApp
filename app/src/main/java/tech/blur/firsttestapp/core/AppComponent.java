package tech.blur.firsttestapp.core;


import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import tech.blur.firsttestapp.core.modules.SharedPreferencesModule;
import tech.blur.firsttestapp.list.ListPresenter;
import tech.blur.firsttestapp.main.MainPresenter;
import tech.blur.firsttestapp.core.modules.ApiModule;

@Singleton
@Component(modules = {ApiModule.class, SharedPreferencesModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);

    void inject(ListPresenter listPresenter);
}
