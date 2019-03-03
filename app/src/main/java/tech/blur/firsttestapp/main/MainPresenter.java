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
import tech.blur.firsttestapp.core.model.PostServer;
import tech.blur.firsttestapp.main.api.QueueApi;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Retrofit retrofit;
    @Inject
    SharedPreferences preferences;
    private QueueApi queueApi;
    private int taskPos = 0;
    private ArrayList<PostServer> postServers = new ArrayList<>();
    private ArrayList<Post> posts;
    private int taskAmount;

    MainPresenter() {
        App.component.inject(this);
        queueApi = retrofit.create(QueueApi.class);

    }

    @SuppressLint("CheckResult")
    void getQueue() {

        int tempPos = PreferencesApi.getPos(preferences);
        if (tempPos != 0) taskPos = tempPos;

        if ((posts = PreferencesApi.getPosts(preferences)) == null) {
            queueApi.getPosts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableSingleObserver<List<PostServer>>() {
                        @Override
                        public void onSuccess(List<PostServer> res) {
                            postServers.addAll(res);
                            convertPosts();
                            taskAmount = postServers.size();
                            getViewState().onDownloadReady();
                            if (taskPos < taskAmount - 1)
                                getViewState().setTask(posts.get(PreferencesApi.getPos(preferences)));
                            else getViewState().onTaskEnd();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        } else{
            taskAmount = posts.size();
            getViewState().onDownloadReady();
            if (taskPos < taskAmount - 1)
                getViewState().setTask(posts.get(PreferencesApi.getPos(preferences)));
            else getViewState().onTaskEnd();
        }
    }


    void onDoneClicked() {
        if (taskPos < taskAmount - 1) nextTask();
        else getViewState().onTaskEnd();
    }

    private void convertPosts() {
        posts = new ArrayList<>();
        for (PostServer postServer : postServers) {
            posts.add(new Post(postServer));
        }
        PreferencesApi.setPosts(posts, preferences);
        postServers.clear();
    }

    void nextTask() {
        PreferencesApi.setPos(++taskPos, preferences);
        getViewState().setTask(posts.get(taskPos));

    }

}
