package tech.blur.firsttestapp.list;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import tech.blur.firsttestapp.core.model.Post;

public interface ListView extends MvpView {

    void onPostsReady(ArrayList<Post> posts);

}
