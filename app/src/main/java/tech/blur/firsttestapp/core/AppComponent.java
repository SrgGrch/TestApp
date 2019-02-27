package tech.blur.firsttestapp.core;


import javax.inject.Singleton;

import dagger.Component;
import tech.blur.firsttestapp.Queue.QueuePresenter;
import tech.blur.firsttestapp.core.modules.ApiModule;

@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {
    void inject(QueuePresenter queuePresenter);
}
