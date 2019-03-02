package tech.blur.firsttestapp.main;

import com.arellomobile.mvp.MvpView;

import tech.blur.firsttestapp.core.model.Post;

interface MainView extends MvpView {

    void setTask(Post post);

    void showMessage(String s);

    void onTaskEnd();

    void onDownloadReady();
}
