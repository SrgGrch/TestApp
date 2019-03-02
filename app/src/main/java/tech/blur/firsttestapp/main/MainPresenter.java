package tech.blur.firsttestapp.main;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import tech.blur.firsttestapp.App;
import tech.blur.firsttestapp.core.PreferencesApi;
import tech.blur.firsttestapp.core.model.Post;
import tech.blur.firsttestapp.main.api.QueueApi;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Retrofit retrofit;
    @Inject
    SharedPreferences preferences;
    private QueueApi queueApi;
    private int taskPos = 0;
    private ArrayList<Post> posts = new ArrayList<>();
    private int taskAmount;

    MainPresenter() {
        App.component.inject(this);
        queueApi = retrofit.create(QueueApi.class);

    }

    @SuppressLint("CheckResult")
    void getQueue() {

        int tempPos = PreferencesApi.getPos(preferences);
        if (tempPos != 0) taskPos = tempPos;

        queueApi.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<List<Post>>() {
                    @Override
                    public void onSuccess(List<Post> res) {
                        posts.addAll(res);
                        taskAmount = posts.size();
                        getViewState().onDownloadReady();
                        getViewState().setTask(posts.get(PreferencesApi.getPos(preferences)));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }



    void onDoneClicked(){
        if (taskPos < taskAmount) nextTask();
        else getViewState().onTaskEnd();
    }

    void nextTask(){
        PreferencesApi.setPos(++taskPos, preferences);
        getViewState().setTask(posts.get(taskPos));

    }

}
